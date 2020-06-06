package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

@Component
public class KeyWordChecker implements EsCardConditionChecker {

    private static final String NAME = "name";

    private static final String EFFECT = "effect";

    @Override
    public void initQueryBuilder(NativeSearchQueryBuilder queryBuilder,
                                 BoolQueryBuilder boolQueryBuilder,
                                 CardSpecificationDTO cardSpecification) {
        if (cardSpecification.getKeyWord() == null) {
           return;
        }

        boolQueryBuilder.must(QueryBuilders.boolQuery()
                .should(QueryBuilders.matchPhraseQuery(NAME, cardSpecification.getKeyWord()))
                .should(QueryBuilders.matchPhraseQuery(EFFECT, cardSpecification.getKeyWord())));
        queryBuilder.withHighlightFields(new HighlightBuilder.Field(getField()).preTags(PRE_TAG).postTags(POST_TAG));
    }

    @Override
    public String getField() {
        return NAME;
    }
}
