package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;
import static cn.mayu.yugioh.common.core.util.AssertUtil.*;

@Component
public class KeyWordChecker extends MoreEsCardConditionChecker {

    private static final String NAME = "name";

    private static final String EFFECT = "effect";

    @Override
    public String getField() {
        return NAME;
    }

    @Override
    protected boolean checkSpecification(CardSpecificationDTO cardSpecification) {
        return isNull(cardSpecification.getKeyWord());
    }

    @Override
    protected Object getSpecification(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getKeyWord();
    }

    @Override
    protected String[] names() {
        return new String[]{ NAME, EFFECT };
    }
}