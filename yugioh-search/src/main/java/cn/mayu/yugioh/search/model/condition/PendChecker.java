package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.springframework.stereotype.Component;
import static cn.mayu.yugioh.common.core.util.AssertUtil.*;

@Component
public class PendChecker extends OneEsCardConditionChecker {

    private static final String PEND = "pend";

    @Override
    protected boolean checkSpecification(CardSpecification cardSpecification) {
        return isNull(cardSpecification.getPend());
    }

    @Override
    protected Object getSpecification(CardSpecification cardSpecification) {
        return cardSpecification.getPend();
    }

    @Override
    public String getField() {
        return PEND;
    }
}
