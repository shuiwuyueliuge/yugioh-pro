package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import java.util.List;

public abstract class ArrayEsCardConditionChecker<T> extends DefaultEsCardConditionChecker {

    @Override
    protected void processSpecification(NativeSearchQueryBuilder queryBuilder, BoolQueryBuilder boolQueryBuilder, CardSpecificationDTO cardSpecification) {
        BoolQueryBuilder arrayBoolQueryBuilder = QueryBuilders.boolQuery();
        getSpecification(cardSpecification).stream()
                .forEach(data -> arrayBoolQueryBuilder.should(QueryBuilders.matchQuery(getField(), data)));
        boolQueryBuilder.must(arrayBoolQueryBuilder);
    }

    protected abstract List<T> getSpecification(CardSpecificationDTO cardSpecification);
}
