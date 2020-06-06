package cn.mayu.yugioh.search.repository;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import cn.mayu.yugioh.search.repository.model.ElasticsearchCardEntity;
import cn.mayu.yugioh.search.repository.model.condition.EsCardConditionChecker;
import cn.mayu.yugioh.search.repository.model.condition.EsCardConditionCheckerChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ElasticSearchRepository {

    private ElasticsearchRestTemplate esTemplate;

    private EsCardConditionCheckerChain checkerChain;

    @Autowired
    public ElasticSearchRepository(ElasticsearchRestTemplate esTemplate, List<EsCardConditionChecker> esCardConditionCheckers) {
        this.esTemplate = esTemplate;
        this.checkerChain = new EsCardConditionCheckerChain(esCardConditionCheckers);
    }

    public SearchHits<ElasticsearchCardEntity> searchCard(CardSpecificationDTO cardSpecification) {
        NativeSearchQueryBuilder queryBuilder = checkerChain.initNativeSearchQueryBuilder(cardSpecification);
        return esTemplate.search(queryBuilder.build(), ElasticsearchCardEntity.class);
    }
}
