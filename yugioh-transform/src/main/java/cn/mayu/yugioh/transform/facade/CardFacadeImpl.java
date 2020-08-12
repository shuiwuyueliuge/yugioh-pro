package cn.mayu.yugioh.transform.facade;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.transform.manager.CardManagerContext;
import cn.mayu.yugioh.common.facade.transform.CardFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CardFacadeImpl implements CardFacade {

    @Autowired
    private CardManagerContext cardManagerContext;

    @Override
    @PostMapping("/card")
    public List<CardDetail> findByIdAndTypeVal(@RequestBody List<CardDetail> details) {
        return cardManagerContext.findByIdAndTypeVal(details);
    }
}
