package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.springframework.stereotype.Component;

@Component
public class DefConditionProvider extends OneConditionProvider {

    @Override
    protected boolean skip(CardSpecification cardSpecification) {
        return cardSpecification.getDef() == null;
    }

    @Override
    protected String getFiledKey() {
        return "def";
    }

    @Override
    protected Object getFiledValue(CardSpecification cardSpecification) {
        return cardSpecification.getDef().equals("?") ? -1 : cardSpecification.getDef();
    }
}
