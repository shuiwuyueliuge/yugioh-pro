package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;

@Component
public class LevelConditionProvider extends OneConditionProvider {

    @Override
    protected boolean skip(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getLevel() == null;
    }

    @Override
    protected String getFiledKey() {
        return "level";
    }

    @Override
    protected Object getFiledValue(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getLevel();
    }
}
