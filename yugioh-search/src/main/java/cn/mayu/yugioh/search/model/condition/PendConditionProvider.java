package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.springframework.stereotype.Component;

@Component
public class PendConditionProvider extends OneConditionProvider {

    @Override
    protected boolean skip(CardSpecification cardSpecification) {
        return cardSpecification.getPend() == null;
    }

    @Override
    protected String getFiledKey() {
        return "pend";
    }

    @Override
    protected Object getFiledValue(CardSpecification cardSpecification) {
        return cardSpecification.getPend();
    }
}
