package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.springframework.stereotype.Component;
import static cn.mayu.yugioh.common.core.util.AssertUtil.*;
import java.util.List;

@Component
public class AttributeChecker extends ArrayEsCardConditionChecker<Integer> {

    private static final String ATTRIBUTE = "attribute";

    @Override
    public String getField() {
        return ATTRIBUTE;
    }

    @Override
    protected boolean checkSpecification(CardSpecification cardSpecification) {
        return isEmpty(cardSpecification.getMonsterAttribute());
    }

    @Override
    protected List<Integer> getSpecification(CardSpecification cardSpecification) {
        return cardSpecification.getMonsterAttribute();
    }
}
