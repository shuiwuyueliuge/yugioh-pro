package cn.mayu.yugioh.transform.facade;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.transform.manager.CardManagerContext;
import cn.yugioh.common.facade.transform.CardFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardFacadeImpl implements CardFacade {

    @Autowired
    private CardManagerContext cardManagerContext;

    @Override
    @GetMapping("/card")
    public CardDetail findByIdAndTypeVal(@RequestParam("id") Integer id,
                                         @RequestParam("typeVal") Integer typeVal) {
        return cardManagerContext.findByIdAndTypeVal(id, typeVal);
    }
}
