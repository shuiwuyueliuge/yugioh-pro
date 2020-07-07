package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.springframework.stereotype.Component;
import static cn.mayu.yugioh.common.core.util.AssertUtil.*;

@Component
public class LevelChecker extends OneEsCardConditionChecker {

    private static final String LEVEL = "level";

    @Override
    protected boolean checkSpecification(CardSpecification cardSpecification) {
        return isNull(cardSpecification.getLevel());
    }

    @Override
    protected Object getSpecification(CardSpecification cardSpecification) {
        return cardSpecification.getLevel();
    }

    @Override
    public String getField() {
        return LEVEL;
    }
}
