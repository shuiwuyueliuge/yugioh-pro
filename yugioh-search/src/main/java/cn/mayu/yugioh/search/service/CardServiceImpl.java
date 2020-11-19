package cn.mayu.yugioh.search.service;

import cn.mayu.yugioh.common.core.util.BeanUtil;
import cn.mayu.yugioh.common.dto.search.CardResponseDTO;
import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import cn.mayu.yugioh.search.repository.ElasticSearchRepository;
import cn.mayu.yugioh.search.model.ElasticsearchCardEntity;
import cn.mayu.yugioh.common.facade.transform.CardFacade;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private ElasticSearchRepository elasticSearchRepository;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ReactiveRedisTemplate<Integer, CardDetail> redisTemplate;

    @Autowired
    private CardFacade cardFacade;

    @Override
    public CardResponnseDTO searchCard(CardSpecificationDTO cardSpecification) {
        // es
        SearchHits<ElasticsearchCardEntity> cards = elasticSearchRepository.searchCard(cardSpecification);
        if (cards.getTotalHits() <= 0) {
            return new CardResponseDTO(cards.getTotalHits(), Lists.newArrayList());
        }

        Cache cardCache = cacheManager.getCache("cardCache");
        List<CardDetail> details = Lists.newArrayList();
        List<CardDetail> noCached = Lists.newArrayList();
        for (SearchHit<ElasticsearchCardEntity> data : cards) {
            ElasticsearchCardEntity entity = data.getContent();
            CardDetail cardDetail = new CardDetail();
            // local cache
            CardDetail cached = cardCache.get(entity.getId(), CardDetail.class);
            if (cached != null) {
                BeanUtil.copyProperties(cached, cardDetail);
                details.add(cardDetail);
                continue;
            }

            // redis
            CardDetail redisCached = redisTemplate.opsForValue().get(entity.getId()).block();
            if (redisCached != null) {
                BeanUtil.copyProperties(redisCached, cardDetail);
                details.add(cardDetail);
            }

            BeanUtils.copyProperties(entity, cardDetail);
            cardDetail.setId(entity.getId());
            cardDetail.setTypeVal(entity.getTypeVal().toString());
            if (entity.getLinkArrow() != null) {
                cardDetail.setLinkArrow(Stream.of(entity.getLinkArrow().split(" ")).collect(Collectors.toList()));
            } else {
                cardDetail.setLinkArrow(Lists.newArrayList());
            }

            if (entity.getTypeSt() != null) {
                cardDetail.setTypeSt(Stream.of(entity.getTypeSt().split(" ")).collect(Collectors.toList()));
            } else {
                cardDetail.setTypeSt(Lists.newArrayList());
            }

            noCached.add(cardDetail);
        }

        // db
        if (noCached.size() > 0) {
            List<CardDetail> dbDetails = cardFacade.findByIdAndTypeVal(noCached);
            if (dbDetails.size() == 0) {
                return new CardResponseDTO(cards.getTotalHits(), noCached);
            }

            dbDetails.stream().forEach(data -> {
                CardDetail cardDetail = new CardDetail();
                cardCache.put(data.getId(), data);
                redisTemplate.opsForValue().set(data.getId(), data).subscribe();
                BeanUtil.copyProperties(data, cardDetail);
                details.add(cardDetail);
            });
        }

        return new CardResponseDTO(cards.getTotalHits(), details);
    }
}
