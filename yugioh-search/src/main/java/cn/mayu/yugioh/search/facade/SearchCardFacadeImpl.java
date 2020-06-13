package cn.mayu.yugioh.search.facade;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import cn.mayu.yugioh.search.service.CardService;
import cn.yugioh.common.facade.search.SearchCardFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SearchCardFacadeImpl implements SearchCardFacade {

    @Autowired
    private CardService cardService;

    @Override
    @GetMapping("/card")
    public List<CardDetail> searchCardByCondition(CardSpecificationDTO cardSpecificationDTO) {
        return cardService.searchCard(cardSpecificationDTO);
    }
}
