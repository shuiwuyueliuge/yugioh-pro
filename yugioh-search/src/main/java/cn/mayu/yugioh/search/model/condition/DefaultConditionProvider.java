package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;

@Component
public abstract class DefaultConditionProvider implements ConditionProvider {

    protected static final String PRE_TAGS = "<strong>";

    protected static final String POST_TAGS = "</strong>";

    protected abstract boolean skip(CardSpecificationDTO cardSpecification);
}
