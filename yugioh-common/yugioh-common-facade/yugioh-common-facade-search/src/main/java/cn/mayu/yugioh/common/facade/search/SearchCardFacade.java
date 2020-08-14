package cn.mayu.yugioh.common.facade.search;

import cn.mayu.yugioh.common.dto.search.CardResponseDTO;
import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "search", contextId = "card")
public interface SearchCardFacade {

    @PostMapping("/card")
    CardResponseDTO searchCardByCondition(@RequestBody CardSpecificationDTO cardSpecificationDTO);
}
