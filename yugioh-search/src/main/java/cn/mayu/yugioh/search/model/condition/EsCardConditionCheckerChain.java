package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.search.sort.SortBuilders.*;
import static org.elasticsearch.search.sort.SortOrder.DESC;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import java.util.List;

public class EsCardConditionCheckerChain {

    private List<EsCardConditionChecker> esCardConditionCheckers;

    private static final String SCORE = "_score";

    public EsCardConditionCheckerChain(List<EsCardConditionChecker> esCardConditionCheckers) {
        this.esCardConditionCheckers = esCardConditionCheckers;
    }

    public NativeSearchQueryBuilder initNativeSearchQueryBuilder(CardSpecification cardSpecification) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        initQueryBuilder(queryBuilder, boolQueryBuilder, cardSpecification);
        queryBuilder.withQuery(boolQueryBuilder)
                    .withSort(fieldSort(SCORE).order(DESC));
        return queryBuilder;
    }

    private void initQueryBuilder(NativeSearchQueryBuilder queryBuilder, BoolQueryBuilder boolQueryBuilder, CardSpecification cardSpecification) {
        esCardConditionCheckers.stream().forEach(checker -> checker.initQueryBuilder(queryBuilder, boolQueryBuilder, cardSpecification));
    }
}
