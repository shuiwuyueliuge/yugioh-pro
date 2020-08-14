package cn.mayu.yugioh.search.facade;

import cn.mayu.yugioh.common.dto.search.CardResponseDTO;
import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import cn.mayu.yugioh.search.service.CardService;
import cn.mayu.yugioh.common.facade.search.SearchCardFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchCardFacadeImpl implements SearchCardFacade {

    @Autowired
    private CardService cardService;

    @Override
    @PostMapping("/card")
    public CardResponseDTO searchCardByCondition(@RequestBody CardSpecificationDTO cardSpecificationDTO) {
        return cardService.searchCard(cardSpecificationDTO);
    }
}
