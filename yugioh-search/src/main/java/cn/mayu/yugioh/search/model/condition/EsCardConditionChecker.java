package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

public interface EsCardConditionChecker {

    void initQueryBuilder(NativeSearchQueryBuilder queryBuilder,
                          BoolQueryBuilder boolQueryBuilder,
                          CardSpecification cardSpecification);

    default String getField() {
        return "";
    }
}
