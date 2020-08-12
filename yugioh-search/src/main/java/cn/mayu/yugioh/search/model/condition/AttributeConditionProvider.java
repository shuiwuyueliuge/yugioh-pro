package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AttributeConditionProvider extends ArrayConditionProvider<Integer> {

    @Override
    protected boolean skip(CardSpecification cardSpecification) {
        return cardSpecification.getMonsterAttribute() == null || cardSpecification.getMonsterAttribute().size() <= 0;
    }

    @Override
    protected String getFiledKey() {
        return "attribute";
    }

    @Override
    protected List<Integer> getFiledValue(CardSpecification cardSpecification) {
        return cardSpecification.getMonsterAttribute();
    }
}
