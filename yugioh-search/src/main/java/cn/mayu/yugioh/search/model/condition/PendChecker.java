package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;
import static cn.mayu.yugioh.common.core.util.AssertUtil.*;

@Component
public class PendChecker extends OneEsCardConditionChecker {

    private static final String PEND = "pend";

    @Override
    protected boolean checkSpecification(CardSpecificationDTO cardSpecification) {
        return isNull(cardSpecification.getPend());
    }

    @Override
    protected Object getSpecification(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getPend();
    }

    @Override
    public String getField() {
        return PEND;
    }
}
