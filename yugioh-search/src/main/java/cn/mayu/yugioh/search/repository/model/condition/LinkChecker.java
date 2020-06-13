package cn.mayu.yugioh.search.repository.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.stereotype.Component;

@Component
public class LinkChecker extends OneEsCardConditionChecker {

    private static final String LINK = "link";

    @Override
    protected boolean checkSpecification(CardSpecificationDTO cardSpecification) {
        return cardSpecification.getLink() == null;
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
