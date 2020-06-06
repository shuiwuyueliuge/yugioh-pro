package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

@Component
public class PendChecker implements EsCardConditionChecker {

    private static final String PEND = "pend";

    @Override
    public void initQueryBuilder(NativeSearchQueryBuilder queryBuilder,
                                 BoolQueryBuilder boolQueryBuilder,
                                 CardSpecificationDTO cardSpecification) {
        if (cardSpecification.getPend() == null) return;
        boolQueryBuilder.must(QueryBuilders.matchQuery(getField(), cardSpecification.getPend()));
        queryBuilder.withHighlightFields(new HighlightBuilder.Field(getField()).preTags(PRE_TAG).postTags(POST_TAG));
    }

    @Override
    public String getField() {
        return PEND;
    }
}
