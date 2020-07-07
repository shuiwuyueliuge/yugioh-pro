package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.springframework.stereotype.Component;
import static cn.mayu.yugioh.common.core.util.AssertUtil.*;

@Component
public class CardTypeChecker extends OneEsCardConditionChecker {

    private static final String TYPE_VAL = "typeVal";

    @Override
    protected boolean checkSpecification(CardSpecification cardSpecification) {
        return isNull(cardSpecification.getCardType());
    }

    @Override
    protected Object getSpecification(CardSpecification cardSpecification) {
        return cardSpecification.getCardType();
    }

    @Override
    public String getField() {
        return TYPE_VAL;
    }
}
