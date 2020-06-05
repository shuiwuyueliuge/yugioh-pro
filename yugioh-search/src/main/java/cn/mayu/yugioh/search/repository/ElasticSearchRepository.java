package cn.mayu.yugioh.search.repository;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import cn.mayu.yugioh.search.repository.model.ElasticsearchCardEntity;
import cn.mayu.yugioh.search.repository.model.condition.EsCardConditionChecker;
import cn.mayu.yugioh.search.repository.model.condition.EsCardConditionCheckerChain;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ElasticSearchRepository {

    private ElasticsearchRestTemplate esTemplate;

    private EsCardConditionCheckerChain checkerChain;

    @Autowired
    public ElasticSearchRepository(ElasticsearchRestTemplate esTemplate, List<EsCardConditionChecker> esCardConditionCheckers) {
        this.esTemplate = esTemplate;
        this.checkerChain = new EsCardConditionCheckerChain(esCardConditionCheckers);
    }

    public List<ElasticsearchCardEntity> searchCard(CardSpecificationDTO cardSpecification) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        checkerChain.initQueryBuilder(boolQueryBuilder, cardSpecification);
        queryBuilder.withQuery(boolQueryBuilder);
        SearchHits<ElasticsearchCardEntity> list = esTemplate.search(queryBuilder.build(), ElasticsearchCardEntity.class);
        return list.stream().map(data -> data.getContent()).collect(Collectors.toList());



//        if (cardSpecification.getKeyWord() != null) {
//            boolQueryBuilder.must(QueryBuilders.boolQuery()
//                    .should(QueryBuilders.matchPhraseQuery("name", cardSpecification.getKeyWord()))
//                    .should(QueryBuilders.matchPhraseQuery("effect", cardSpecification.getKeyWord())));
//        }

//        if (cardSpecification.getCardType() != null) {
//            boolQueryBuilder.must(QueryBuilders.matchQuery("typeVal", cardSpecification.getCardType()));
//        }

//        if (cardSpecification.getAtk() != null) {
//            boolQueryBuilder.must(QueryBuilders.matchQuery("atk", cardSpecification.getAtk()));
//        }

//        if (cardSpecification.getDef() != null) {
//            boolQueryBuilder.must(QueryBuilders.matchQuery("def", cardSpecification.getDef()));
//        }

//        if (cardSpecification.getLevel() != null) {
//            boolQueryBuilder.must(QueryBuilders.matchQuery("level", cardSpecification.getLevel()));
//        }

//        if (cardSpecification.getPend() != null) {
//            boolQueryBuilder.must(QueryBuilders.matchQuery("pend", cardSpecification.getPend()));
//        }

//        if (cardSpecification.getLink() != null) {
//            boolQueryBuilder.must(QueryBuilders.matchQuery("link", cardSpecification.getLink()));
//        }

//        if (cardSpecification.getMonsterAttribute() != null && cardSpecification.getMonsterAttribute().size() > 0) {
//            BoolQueryBuilder attributeBoolQueryBuilder = QueryBuilders.boolQuery();
//            cardSpecification.getMonsterAttribute().stream().forEach(data ->
//                    attributeBoolQueryBuilder.should(QueryBuilders.matchQuery("attribute", data)));
//            boolQueryBuilder.must(attributeBoolQueryBuilder);
//        }

//        if (cardSpecification.getMonsterRace() != null && cardSpecification.getMonsterRace().size() > 0) {
//            BoolQueryBuilder raceBoolQueryBuilder = QueryBuilders.boolQuery();
//            cardSpecification.getMonsterRace().stream().forEach(data ->
//                    raceBoolQueryBuilder.should(QueryBuilders.matchQuery("race", data)));
//            boolQueryBuilder.must(raceBoolQueryBuilder);
//        }
    }
}
