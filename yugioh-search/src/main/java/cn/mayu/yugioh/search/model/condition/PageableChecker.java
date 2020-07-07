package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.springframework.stereotype.Component;
import static cn.mayu.yugioh.common.core.util.AssertUtil.*;

@Component
public class PageableChecker extends PageEsCardConditionChecker {

    @Override
    protected boolean checkSpecification(CardSpecification cardSpecification) {
        return isNull(cardSpecification.getPage()) || isNull(cardSpecification.getSize());
    }
}
