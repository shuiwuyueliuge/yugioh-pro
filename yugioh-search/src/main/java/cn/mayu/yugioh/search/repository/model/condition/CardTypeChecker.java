package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;

@Component
public class CardTypeChecker extends OneEsCardConditionChecker {

    private static final String TYPE_VAL = "typeVal";

    @Override
    protected boolean checkSpecification(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getCardType() == null;
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
