package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

public abstract class PageEsCardConditionChecker extends DefaultEsCardConditionChecker {

    @Override
    protected void processSpecification(NativeSearchQueryBuilder queryBuilder, BoolQueryBuilder boolQueryBuilder, CardSpecificationDTO cardSpecification) {
        if (!checkSpecification(cardSpecification)) return;
        queryBuilder.withPageable(PageRequest.of(cardSpecification.getPage(), cardSpecification.getSize()));
    }
}
