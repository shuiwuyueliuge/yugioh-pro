package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

public interface EsCardConditionChecker {

    String PRE_TAG = "<strong>";

    String POST_TAG = "</strong>";

    void initQueryBuilder(NativeSearchQueryBuilder queryBuilder,
                          BoolQueryBuilder boolQueryBuilder,
                          CardSpecificationDTO cardSpecification);

    default String getField() {
        return "";
    }
}
