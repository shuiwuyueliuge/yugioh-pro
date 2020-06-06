package cn.mayu.yugioh.search.service;

import cn.mayu.yugioh.common.dto.cardsource.CardDetail;
import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import cn.mayu.yugioh.search.repository.ElasticSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private ElasticSearchRepository elasticSearchRepository;

    @Override
    public List<CardDetail> searchCard(CardSpecificationDTO cardSpecification) {
        // es查询数据
        //  SearchHits<ElasticsearchCardEntity> cards = elasticSearchRepository.searchCard(cardSpecification);
        // 本地查询
        // redis查询
        // mysql查询
        return null;
    }
}
