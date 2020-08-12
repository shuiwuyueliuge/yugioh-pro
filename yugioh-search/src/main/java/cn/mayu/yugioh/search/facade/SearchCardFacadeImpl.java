package cn.mayu.yugioh.search.facade;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.search.CardSpecification;
import cn.mayu.yugioh.search.service.CardService;
import cn.mayu.yugioh.common.facade.search.SearchCardFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SearchCardFacadeImpl implements SearchCardFacade {

    @Autowired
    private CardService cardService;

    @Override
    @PostMapping("/card")
    public List<CardDetail> searchCardByCondition(CardSpecification cardSpecificationDTO) {
        return cardService.searchCard(cardSpecificationDTO);
    }
}
