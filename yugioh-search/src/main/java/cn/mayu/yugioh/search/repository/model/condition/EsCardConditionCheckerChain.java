package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import java.util.List;

public class EsCardConditionCheckerChain implements EsCardConditionChecker {

    private List<EsCardConditionChecker> esCardConditionCheckers;

    public EsCardConditionCheckerChain(List<EsCardConditionChecker> esCardConditionCheckers) {
        this.esCardConditionCheckers = esCardConditionCheckers;
    }

    @Override
    public void initQueryBuilder(BoolQueryBuilder boolQueryBuilder, CardSpecificationDTO cardSpecification) {
        esCardConditionCheckers.stream().forEach(checker -> checker.initQueryBuilder(boolQueryBuilder, cardSpecification));
    }
}
