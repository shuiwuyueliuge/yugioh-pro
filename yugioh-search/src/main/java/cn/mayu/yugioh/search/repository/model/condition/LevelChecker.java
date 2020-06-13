package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;

@Component
public class LevelChecker extends OneEsCardConditionChecker {

    private static final String LEVEL = "level";

    @Override
    protected boolean checkSpecification(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getLevel() == null;
    }

    @Override
    protected Object getSpecification(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getLevel();
    }

    @Override
    public String getField() {
        return LEVEL;
    }
}
