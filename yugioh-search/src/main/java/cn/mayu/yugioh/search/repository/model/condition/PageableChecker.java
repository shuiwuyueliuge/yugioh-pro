package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

@Component
public class PageableChecker implements EsCardConditionChecker {

    @Override
    public void initQueryBuilder(NativeSearchQueryBuilder queryBuilder,
                                 BoolQueryBuilder boolQueryBuilder,
                                 CardSpecificationDTO cardSpecification) {
        if (cardSpecification.getPage() == null || cardSpecification.getSize() == null) return;
        queryBuilder.withPageable(PageRequest.of(cardSpecification.getPage(), cardSpecification.getSize()));
    }
}
