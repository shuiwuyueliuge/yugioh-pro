package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;

@Component
public class PendChecker extends OneEsCardConditionChecker {

    private static final String PEND = "pend";

    @Override
    protected boolean checkSpecification(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getPend() == null;
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
