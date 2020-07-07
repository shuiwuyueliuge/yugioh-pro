package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

public abstract class OneEsCardConditionChecker extends DefaultEsCardConditionChecker {

    @Override
    protected void processSpecification(NativeSearchQueryBuilder queryBuilder, BoolQueryBuilder boolQueryBuilder, CardSpecification cardSpecification) {
        boolQueryBuilder.must(QueryBuilders.matchQuery(getField(), getSpecification(cardSpecification)));
    }

    protected abstract Object getSpecification(CardSpecification cardSpecification);
}
