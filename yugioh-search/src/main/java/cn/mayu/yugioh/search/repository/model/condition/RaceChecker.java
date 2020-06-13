package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RaceChecker extends ArrayEsCardConditionChecker<Integer> {

    private static final String RACE = "race";

    @Override
    public String getField() {
        return RACE;
    }

    @Override
    protected boolean checkSpecification(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getMonsterRace() == null || cardSpecification.getMonsterRace().size() <= 0;
    }

    @Override
    protected List<Integer> getSpecification(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getMonsterRace();
    }
}
