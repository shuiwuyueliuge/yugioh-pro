package cn.yugioh.common.facade.search;

import cn.mayu.yugioh.common.dto.cardsource.CardDetail;
import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "sync-home")
public interface SearchCardFacade {

    @GetMapping("/card")
    List<CardDetail> searchCardByCondition(CardSpecificationDTO cardSpecificationDTO);
}
