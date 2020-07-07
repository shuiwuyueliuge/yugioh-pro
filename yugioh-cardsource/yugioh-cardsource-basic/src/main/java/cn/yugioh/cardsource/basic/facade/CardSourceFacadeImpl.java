package cn.yugioh.cardsource.basic.facade;

import cn.mayu.yugioh.common.dto.cardsource.SourceType;
import cn.mayu.yugioh.common.facade.cardsource.CardSourceFacade;
import static cn.yugioh.cardsource.basic.manager.CardSourceEnum.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CardSourceFacadeImpl implements CardSourceFacade {

    @Override
    @GetMapping("/card-source/type")
    public List<SourceType> getSourceType() {
        return sources().stream().map(data -> new SourceType(data.toString(), data.getType())).collect(Collectors.toList());
    }
}
