package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;

@Component
public class AttributeChecker implements EsCardConditionChecker {

    @Override
    public void initQueryBuilder(BoolQueryBuilder boolQueryBuilder, CardSpecificationDTO cardSpecification) {
        if (cardSpecification.getMonsterAttribute() != null && cardSpecification.getMonsterAttribute().size() > 0) {
            return;
        }

        BoolQueryBuilder attributeBoolQueryBuilder = QueryBuilders.boolQuery();
        cardSpecification.getMonsterAttribute().stream().forEach(data ->
                attributeBoolQueryBuilder.should(QueryBuilders.matchQuery("attribute", data)));
        boolQueryBuilder.must(attributeBoolQueryBuilder);
    }
}
