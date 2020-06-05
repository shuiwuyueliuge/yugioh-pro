package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;

@Component
public class LevelChecker implements EsCardConditionChecker {

    @Override
    public void initQueryBuilder(BoolQueryBuilder boolQueryBuilder, CardSpecificationDTO cardSpecification) {
        if (cardSpecification.getLevel() == null) {
            return;
        }

        boolQueryBuilder.must(QueryBuilders.matchQuery("level", cardSpecification.getLevel()));
    }
}
