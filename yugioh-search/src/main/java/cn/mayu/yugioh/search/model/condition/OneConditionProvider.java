package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public abstract class OneConditionProvider extends DefaultConditionProvider {

    @Override
    public void buildCondition(CardSpecificationDTO cardSpecification, BoolQueryBuilder boolQueryBuilder, List<HighlightBuilder.Field> fields) {
        if (skip(cardSpecification)) {
            return;
        }

        boolQueryBuilder.must(QueryBuilders.matchQuery(getFiledKey(), getFiledValue(cardSpecification)));
        fields.add(new HighlightBuilder.Field(getFiledKey()).preTags(PRE_TAGS).postTags(POST_TAGS));
    }

    protected abstract String getFiledKey();

    protected abstract Object getFiledValue(CardSpecificationDTO cardSpecification);
}
