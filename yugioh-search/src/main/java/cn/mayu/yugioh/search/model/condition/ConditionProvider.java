package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import java.util.List;

public interface ConditionProvider {

    void buildCondition(CardSpecification cardSpecification, BoolQueryBuilder boolQueryBuilder, List<HighlightBuilder.Field> fields);
}
