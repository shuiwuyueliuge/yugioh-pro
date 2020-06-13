package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;
import static cn.mayu.yugioh.common.core.util.AssertUtil.*;

@Component
public class LinkChecker extends OneEsCardConditionChecker {

    private static final String LINK = "link";

    @Override
    protected boolean checkSpecification(CardSpecificationDTO cardSpecification) {
        return isNull(cardSpecification.getLink());
    }

    @Override
    protected Object getSpecification(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getLink();
    }

    @Override
    public String getField() {
        return LINK;
    }
}
