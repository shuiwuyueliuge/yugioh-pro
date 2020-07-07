package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.springframework.stereotype.Component;
import static cn.mayu.yugioh.common.core.util.AssertUtil.*;

@Component
public class LinkChecker extends OneEsCardConditionChecker {

    private static final String LINK = "link";

    @Override
    protected boolean checkSpecification(CardSpecification cardSpecification) {
        return isNull(cardSpecification.getLink());
    }

    @Override
    protected Object getSpecification(CardSpecification cardSpecification) {
        return cardSpecification.getLink();
    }

    @Override
    public String getField() {
        return LINK;
    }
}
