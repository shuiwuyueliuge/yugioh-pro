package cn.mayu.yugioh.search.model.condition;

import static cn.mayu.yugioh.common.core.util.AssertUtil.*;
import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.springframework.stereotype.Component;

@Component
public class AtkChecker extends OneEsCardConditionChecker {

    private static final String ATK = "atk";

    @Override
    public String getField() {
        return ATK;
    }

    @Override
    protected boolean checkSpecification(CardSpecification cardSpecification) {
        return isNull(cardSpecification.getAtk());
    }

    @Override
    protected Object getSpecification(CardSpecification cardSpecification) {
        return cardSpecification.getAtk();
    }
}
