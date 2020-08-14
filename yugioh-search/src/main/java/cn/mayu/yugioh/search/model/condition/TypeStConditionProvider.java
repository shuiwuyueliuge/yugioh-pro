package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TypeStConditionProvider extends ArrayConditionProvider<Integer> {

    @Override
    protected boolean skip(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getMonsterType() == null || cardSpecification.getMonsterType().size() <= 0;
    }

    @Override
    protected String getFiledKey() {
        return "typeSt";
    }

    @Override
    protected List<Integer> getFiledValue(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getMonsterType();
    }
}
