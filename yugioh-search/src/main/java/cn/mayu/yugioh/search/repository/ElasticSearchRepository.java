package cn.mayu.yugioh.search.repository;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import cn.mayu.yugioh.search.model.ElasticsearchCardEntity;
import cn.mayu.yugioh.search.model.condition.ConditionProviderChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Component;

@Component
public class ElasticSearchRepository {

    @Autowired
    private ElasticsearchRestTemplate esTemplate;

    @Autowired
    private ConditionProviderChain conditionProviderChain;

    public SearchHits<ElasticsearchCardEntity> searchCard(CardSpecificationDTO cardSpecification) {
        return esTemplate.search(conditionProviderChain.buildCondition(cardSpecification), ElasticsearchCardEntity.class);
    }
}
