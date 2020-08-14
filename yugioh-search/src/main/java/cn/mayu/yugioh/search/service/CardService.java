package cn.mayu.yugioh.search.service;

import cn.mayu.yugioh.common.dto.search.CardResponseDTO;
import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;

public interface CardService {

    CardResponseDTO searchCard(CardSpecificationDTO cardSpecification);
}
