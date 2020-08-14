package cn.mayu.yugioh.manager.service;

import cn.mayu.yugioh.common.dto.search.CardResponseDTO;
import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.facade.search.SearchCardFacade;
import cn.mayu.yugioh.common.web.core.model.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private SearchCardFacade searchCardFacade;

    @Override
    public PageVO<List<CardDetail>> searchCardDetail(CardSpecificationDTO specification) {
        CardResponseDTO response = searchCardFacade.searchCardByCondition(specification);
        PageVO<List<CardDetail>> page = new PageVO<>();
        page.setCount(response.getCount());
        page.setData(response.getCardDetails());
        page.setCurrentPage(specification.getPage());
        return page;
    }
}
