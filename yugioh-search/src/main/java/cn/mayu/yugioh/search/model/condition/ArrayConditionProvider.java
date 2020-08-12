package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public abstract class ArrayConditionProvider<T> extends DefaultConditionProvider {

    @Override
    public void buildCondition(CardSpecification cardSpecification, BoolQueryBuilder boolQueryBuilder, List<HighlightBuilder.Field> fields) {
        if (skip(cardSpecification)) {
            return;
        }

        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        getFiledValue(cardSpecification).stream().forEach(data -> builder.should(QueryBuilders.matchQuery(getFiledKey(), data)));
        boolQueryBuilder.must(builder);
        fields.add(new HighlightBuilder.Field(getFiledKey()).preTags(PRE_TAGS).postTags(POST_TAGS));
    }

    protected abstract String getFiledKey();

    protected abstract List<T> getFiledValue(CardSpecification cardSpecification);
}
