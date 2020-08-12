package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;
import java.util.List;
import static org.elasticsearch.search.sort.SortBuilders.fieldSort;
import static org.elasticsearch.search.sort.SortOrder.DESC;

@Component
public class ConditionProviderChain {

    @Autowired
    private List<ConditionProvider> providers;

    public NativeSearchQuery buildCondition(CardSpecification cardSpecification) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        List<HighlightBuilder.Field> fields = Lists.newArrayList();
        buildCondition(cardSpecification, boolQueryBuilder, fields);
        if (cardSpecification.getPage() != null && cardSpecification.getSize() != null) {
            queryBuilder.withPageable(PageRequest.of(cardSpecification.getPage(), cardSpecification.getSize()));
        }

        queryBuilder.withQuery(boolQueryBuilder)
                .withSort(fieldSort("_score").order(DESC))
                .withHighlightFields(fields.toArray(new HighlightBuilder.Field[fields.size()]));
        return queryBuilder.build();
    }

    protected void buildCondition(CardSpecification cardSpecification, BoolQueryBuilder boolQueryBuilder, List<HighlightBuilder.Field> fields) {
        providers.stream().forEach(data -> data.buildCondition(cardSpecification, boolQueryBuilder, fields));
    }
}
