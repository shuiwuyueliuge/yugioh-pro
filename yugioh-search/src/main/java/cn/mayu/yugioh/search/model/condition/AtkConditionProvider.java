package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.springframework.stereotype.Component;

@Component
public class AtkConditionProvider extends OneConditionProvider {

    @Override
    protected boolean skip(CardSpecification cardSpecification) {
        return cardSpecification.getAtk() == null;
    }

    @Override
    protected String getFiledKey() {
        return "atk";
    }

    @Override
    protected Object getFiledValue(CardSpecification cardSpecification) {
        return cardSpecification.getAtk().equals("?") ? -1 : cardSpecification.getAtk();
    }
}
