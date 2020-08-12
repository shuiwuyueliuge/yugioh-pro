package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.springframework.stereotype.Component;

@Component
public class LevelConditionProvider extends OneConditionProvider {

    @Override
    protected boolean skip(CardSpecification cardSpecification) {
        return cardSpecification.getLevel() == null;
    }

    @Override
    protected String getFiledKey() {
        return "level";
    }

    @Override
    protected Object getFiledValue(CardSpecification cardSpecification) {
        return cardSpecification.getLevel();
    }
}
