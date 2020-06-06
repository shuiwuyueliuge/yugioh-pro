package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

@Component
public class AttributeChecker implements EsCardConditionChecker {

    private static final String ATTRIBUTE = "attribute";

    @Override
    public void initQueryBuilder(NativeSearchQueryBuilder queryBuilder,
                                 BoolQueryBuilder boolQueryBuilder,
                                 CardSpecificationDTO cardSpecification) {
        if (cardSpecification.getMonsterAttribute() == null || cardSpecification.getMonsterAttribute().size() <= 0) {
            return;
        }

        BoolQueryBuilder attributeBoolQueryBuilder = QueryBuilders.boolQuery();
        cardSpecification.getMonsterAttribute().stream().forEach(data ->
                attributeBoolQueryBuilder.should(QueryBuilders.matchQuery(getField(), data)));
        boolQueryBuilder.must(attributeBoolQueryBuilder);
        queryBuilder.withHighlightFields(new HighlightBuilder.Field(getField()).preTags(PRE_TAG).postTags(POST_TAG));
    }

    @Override
    public String getField() {
        return ATTRIBUTE;
    }
}
