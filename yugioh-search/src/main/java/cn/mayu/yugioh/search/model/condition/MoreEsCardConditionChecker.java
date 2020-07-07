package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.elasticsearch.index.query.BoolQueryBuilder;
import static org.elasticsearch.index.query.QueryBuilders.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import java.util.stream.Stream;

public abstract class MoreEsCardConditionChecker extends DefaultEsCardConditionChecker {

    @Override
    protected void processSpecification(NativeSearchQueryBuilder queryBuilder, BoolQueryBuilder boolQueryBuilder, CardSpecification cardSpecification) {
        BoolQueryBuilder builder = boolQueryBuilder.must(boolQuery());
        Stream.of(names()).forEach(name -> builder.should(matchPhraseQuery(name, getSpecification(cardSpecification))));
    }

    protected abstract String[] names();

    protected abstract Object getSpecification(CardSpecification cardSpecification);
}
