package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;

@Component
public class RaceChecker implements EsCardConditionChecker {

    @Override
    public void initQueryBuilder(BoolQueryBuilder boolQueryBuilder, CardSpecificationDTO cardSpecification) {
        if (cardSpecification.getMonsterRace() != null && cardSpecification.getMonsterRace().size() > 0) {
           return;
        }

        BoolQueryBuilder raceBoolQueryBuilder = QueryBuilders.boolQuery();
        cardSpecification.getMonsterRace().stream().forEach(data ->
                raceBoolQueryBuilder.should(QueryBuilders.matchQuery("race", data)));
        boolQueryBuilder.must(raceBoolQueryBuilder);
    }
}
