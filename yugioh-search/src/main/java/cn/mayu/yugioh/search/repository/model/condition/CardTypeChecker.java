package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

@Component
public class CardTypeChecker implements EsCardConditionChecker {

    private static final String TYPE_VAL = "typeVal";

    @Override
    public void initQueryBuilder(NativeSearchQueryBuilder queryBuilder,
                                 BoolQueryBuilder boolQueryBuilder,
                                 CardSpecificationDTO cardSpecification) {
        if (cardSpecification.getCardType() == null) return;
        boolQueryBuilder.must(QueryBuilders.matchQuery(getField(), cardSpecification.getCardType()));
    }

    @Override
    public String getField() {
        return TYPE_VAL;
    }
}
