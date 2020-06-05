package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;

@Component
public class CardTypeChecker implements EsCardConditionChecker {

    @Override
    public void initQueryBuilder(BoolQueryBuilder boolQueryBuilder, CardSpecificationDTO cardSpecification) {
        if (cardSpecification.getCardType() != null) {
            return;
        }

        boolQueryBuilder.must(QueryBuilders.matchQuery("typeVal", cardSpecification.getCardType()));
    }
}
