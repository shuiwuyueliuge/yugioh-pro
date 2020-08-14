package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;

@Component
public class AtkConditionProvider extends OneConditionProvider {

    @Override
    protected boolean skip(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getAtk() == null;
    }

    @Override
    protected String getFiledKey() {
        return "atk";
    }

    @Override
    protected Object getFiledValue(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getAtk().equals("?") ? -1 : cardSpecification.getAtk();
    }
}
