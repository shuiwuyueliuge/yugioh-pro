package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;
import static cn.mayu.yugioh.common.core.util.AssertUtil.*;

@Component
public class CardTypeChecker extends OneEsCardConditionChecker {

    private static final String TYPE_VAL = "typeVal";

    @Override
    protected boolean checkSpecification(CardSpecificationDTO cardSpecification) {
        return isNull(cardSpecification.getCardType());
    }

    @Override
    protected Object getSpecification(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getCardType();
    }

    @Override
    public String getField() {
        return TYPE_VAL;
    }
}
