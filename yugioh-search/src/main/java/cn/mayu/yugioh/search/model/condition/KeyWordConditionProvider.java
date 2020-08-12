package cn.mayu.yugioh.search.model.condition;

import cn.mayu.yugioh.common.dto.search.CardSpecification;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KeyWordConditionProvider extends MoreConditionProvider {

    @Override
    protected boolean skip(CardSpecification cardSpecification) {
        return StringUtils.isEmpty(cardSpecification.getKeyWord());
    }

    @Override
    protected Map<String, Object> getFiledMap(CardSpecification cardSpecification) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("name", cardSpecification.getKeyWord());
        map.put("effect", cardSpecification.getKeyWord());
        return map;
    }
}
