package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RaceConditionProvider extends ArrayConditionProvider<Integer> {

    @Override
    protected boolean skip(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getMonsterRace() == null || cardSpecification.getMonsterRace().size() <= 0;
    }

    @Override
    protected String getFiledKey() {
        return "race";
    }

    @Override
    protected List<Integer> getFiledValue(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getMonsterRace();
    }
}
