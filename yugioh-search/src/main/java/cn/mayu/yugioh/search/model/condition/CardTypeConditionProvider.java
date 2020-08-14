package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;

@Component
public class CardTypeConditionProvider extends OneConditionProvider {

    @Override
    protected boolean skip(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getCardType() == null;
    }

    @Override
    protected String getFiledKey() {
        return "typeVal";
    }

    @Override
    protected Object getFiledValue(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getCardType();
    }
}
