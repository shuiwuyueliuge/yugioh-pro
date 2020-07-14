package cn.mayu.yugioh.search.service;

import cn.mayu.yugioh.common.core.util.BeanUtil;
import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.search.CardSpecification;
import cn.mayu.yugioh.search.repository.ElasticSearchRepository;
import cn.mayu.yugioh.search.model.ElasticsearchCardEntity;
import cn.mayu.yugioh.common.facade.transform.CardFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<CardDetail> searchCard(CardSpecification cardSpecification) {
        // es查询数据
        SearchHits<ElasticsearchCardEntity> cards = elasticSearchRepository.searchCard(cardSpecification);
        Cache cardCache = cacheManager.getCache("cardCache");
        return cards.stream().map(data -> {
            ElasticsearchCardEntity entity = data.getContent();
            CardDetail cardDetail = new CardDetail();
            // 本地查询
            CardDetail cached = cardCache.get(entity.getId(), CardDetail.class);
            if (cached != null) {
                BeanUtil.copyProperties(cached, cardDetail);
            } else {
                // redis查询
                CardDetail redisCached = redisTemplate.opsForValue().get(entity.getId()).block();
                if (redisCached != null) {
                    BeanUtil.copyProperties(redisCached, cardDetail);
                }

                // mysql查询
                CardDetail dbCard = cardFacade.findByIdAndTypeVal(entity.getId(), entity.getTypeVal());
                BeanUtil.copyProperties(dbCard, cardDetail);
            }

            cardDetail.setName(entity.getName());
            cardDetail.setDesc(entity.getEffect());
            cardDetail.setLink(entity.getLink() == null ? null : entity.getLink().toString());
            cardDetail.setDef(entity.getDef() == null ? null : entity.getDef().toString());
            cardDetail.setAtk(entity.getAtk() == null ? null : entity.getAtk().toString());
            cardDetail.setPendL(entity.getPend() == null ? null : entity.getPend().toString());
            cardDetail.setPendR(entity.getPend() == null ? null : entity.getPend().toString());
            cardDetail.setRace(entity.getRace() == null ? null : entity.getRace().toString());
            cardDetail.setId(entity.getId());
            cardDetail.setTypeVal(entity.getTypeVal() == null ? null : entity.getTypeVal().toString());
            cardDetail.setAttribute(entity.getAttribute() == null ? null : entity.getAttribute().toString());
            cardDetail.setLevel(entity.getLevel());
            cardCache.put(entity.getId(), cardDetail);
            redisTemplate.opsForValue().set(entity.getId(), cardDetail).subscribe();
            return cardDetail;
        }).collect(Collectors.toList());
    }
}
