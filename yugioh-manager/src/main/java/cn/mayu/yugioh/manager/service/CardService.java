package cn.mayu.yugioh.manager.service;

import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.web.core.model.vo.PageVO;
import java.util.List;

public interface CardService {

    PageVO<List<CardDetail>> searchCardDetail(CardSpecificationDTO specification);
}
