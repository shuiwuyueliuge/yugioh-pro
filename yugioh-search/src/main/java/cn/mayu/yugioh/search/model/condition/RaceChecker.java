package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.springframework.stereotype.Component;
import static cn.mayu.yugioh.common.core.util.AssertUtil.*;
import java.util.List;

@Component
public class RaceChecker extends ArrayEsCardConditionChecker<Integer> {

    private static final String RACE = "race";

    @Override
    public String getField() {
        return RACE;
    }

    @Override
    protected boolean checkSpecification(CardSpecification cardSpecification) {
        return isEmpty(cardSpecification.getMonsterRace());
    }

    @Override
    protected List<Integer> getSpecification(CardSpecification cardSpecification) {
        return cardSpecification.getMonsterRace();
    }
}
