package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
public abstract class MoreConditionProvider extends DefaultConditionProvider {

    @Override
    public void buildCondition(CardSpecificationDTO cardSpecification, BoolQueryBuilder boolQueryBuilder, List<HighlightBuilder.Field> fields) {
        if (skip(cardSpecification)) {
            return;
        }

        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        getFiledMap(cardSpecification).entrySet().forEach(data -> {
            builder.should(QueryBuilders.matchQuery(data.getKey(), data.getValue()));
            fields.add(new HighlightBuilder.Field(data.getKey()).preTags(PRE_TAGS).postTags(POST_TAGS));
        });

        boolQueryBuilder.must(builder);
    }

    protected abstract Map<String, Object> getFiledMap(CardSpecificationDTO cardSpecification);
}
