package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

@Component
public class RaceChecker implements EsCardConditionChecker {

    private static final String RACE = "race";

    @Override
    public void initQueryBuilder(NativeSearchQueryBuilder queryBuilder,
                                 BoolQueryBuilder boolQueryBuilder,
                                 CardSpecificationDTO cardSpecification) {
        if (cardSpecification.getMonsterRace() == null || cardSpecification.getMonsterRace().size() <= 0) {
           return;
        }

        BoolQueryBuilder raceBoolQueryBuilder = QueryBuilders.boolQuery();
        cardSpecification.getMonsterRace().stream().forEach(data ->
                raceBoolQueryBuilder.should(QueryBuilders.matchQuery(getField(), data)));
        boolQueryBuilder.must(raceBoolQueryBuilder);
        queryBuilder.withHighlightFields(new HighlightBuilder.Field(getField()).preTags(PRE_TAG).postTags(POST_TAG));
    }

    @Override
    public String getField() {
        return RACE;
    }
}
