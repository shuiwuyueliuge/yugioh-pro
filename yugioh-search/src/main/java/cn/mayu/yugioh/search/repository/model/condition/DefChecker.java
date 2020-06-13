package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;

@Component
public class DefChecker extends OneEsCardConditionChecker {

    private static final String DEF = "def";

    @Override
    public String getField() {
        return DEF;
    }

    @Override
    protected boolean checkSpecification(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getDef() == null;
    }

    @Override
    protected Object getSpecification(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getDef();
    }
}
