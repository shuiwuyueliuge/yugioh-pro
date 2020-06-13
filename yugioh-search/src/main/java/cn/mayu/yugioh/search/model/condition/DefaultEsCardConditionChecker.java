package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

public abstract class DefaultEsCardConditionChecker implements EsCardConditionChecker {

    private static final String PRE_TAG = "<strong>";

    private static final String POST_TAG = "</strong>";

    @Override
    public void initQueryBuilder(NativeSearchQueryBuilder queryBuilder, BoolQueryBuilder boolQueryBuilder, CardSpecificationDTO cardSpecification) {
        if (checkSpecification(cardSpecification)) return;
        processSpecification(queryBuilder, boolQueryBuilder, cardSpecification);
        if(getField().equals("")) return;
        queryBuilder.withHighlightFields(new HighlightBuilder.Field(getField()).preTags(PRE_TAG).postTags(POST_TAG));
    }

    protected abstract void processSpecification(NativeSearchQueryBuilder queryBuilder, BoolQueryBuilder boolQueryBuilder, CardSpecificationDTO cardSpecification);

    protected abstract boolean checkSpecification(CardSpecificationDTO cardSpecification);
}
