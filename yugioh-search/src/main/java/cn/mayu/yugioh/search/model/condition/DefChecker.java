package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;
import static cn.mayu.yugioh.common.core.util.AssertUtil.*;

@Component
public class DefChecker extends OneEsCardConditionChecker {

    private static final String DEF = "def";

    @Override
    public String getField() {
        return DEF;
    }

    @Override
    protected boolean checkSpecification(CardSpecificationDTO cardSpecification) {
        return isNull(cardSpecification.getDef());
    }

    @Override
    protected Object getSpecification(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getDef();
    }
}
