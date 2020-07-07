package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

public abstract class PageEsCardConditionChecker extends DefaultEsCardConditionChecker {

    @Override
    protected void processSpecification(NativeSearchQueryBuilder queryBuilder, BoolQueryBuilder boolQueryBuilder, CardSpecification cardSpecification) {
        queryBuilder.withPageable(PageRequest.of(cardSpecification.getPage(), cardSpecification.getSize()));
    }
}
