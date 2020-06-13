package cn.mayu.yugioh.search.model.condition;

import static cn.mayu.yugioh.common.core.util.AssertUtil.*;
import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;

@Component
public class AtkChecker extends OneEsCardConditionChecker {

    private static final String ATK = "atk";

    @Override
    public String getField() {
        return ATK;
    }

    @Override
    protected boolean checkSpecification(CardSpecificationDTO cardSpecification) {
        return isNull(cardSpecification.getAtk());
    }

    @Override
    protected Object getSpecification(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getAtk();
    }
}
