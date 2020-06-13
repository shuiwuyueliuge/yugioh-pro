package cn.mayu.yugioh.search.service;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import java.util.List;

public interface CardService {

    List<CardDetail> searchCard(CardSpecificationDTO cardSpecification);
}
