package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;

@Component
public class LinkConditionProvider extends OneConditionProvider {

    @Override
    protected boolean skip(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getLink() == null;
    }

    @Override
    protected String getFiledKey() {
        return "link";
    }

    @Override
    protected Object getFiledValue(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getLink();
    }
}
