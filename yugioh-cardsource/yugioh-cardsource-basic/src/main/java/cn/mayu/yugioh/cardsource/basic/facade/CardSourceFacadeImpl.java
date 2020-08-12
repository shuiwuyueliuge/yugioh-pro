package cn.mayu.yugioh.cardsource.basic.facade;

import cn.mayu.yugioh.cardsource.basic.service.CardSourceService;
import cn.mayu.yugioh.common.dto.cardsource.SourceType;
import cn.mayu.yugioh.common.facade.cardsource.CardSourceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CardSourceFacadeImpl implements CardSourceFacade {

    @Autowired
    private CardSourceService cardSourceService;

    @Override
    @GetMapping("/source/type")
    public List<SourceType> getSourceType() {
        return cardSourceService.getSourceType();
    }
}
