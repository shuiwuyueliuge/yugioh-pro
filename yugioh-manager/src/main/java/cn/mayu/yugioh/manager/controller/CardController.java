package cn.mayu.yugioh.manager.controller;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.web.core.model.vo.PageVO;
import cn.mayu.yugioh.manager.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/card")
    public PageVO<List<CardDetail>> searchCardDetail(CardSpecificationDTO specification) {
        return cardService.searchCardDetail(specification);
    }
}
