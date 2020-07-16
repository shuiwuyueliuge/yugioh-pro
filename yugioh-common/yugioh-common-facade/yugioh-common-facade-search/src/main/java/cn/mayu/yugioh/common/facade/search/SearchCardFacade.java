package cn.mayu.yugioh.common.facade.search;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.search.CardSpecification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "search")
public interface SearchCardFacade {

    @PostMapping("/card")
    List<CardDetail> searchCardByCondition(CardSpecification cardSpecificationDTO);
}
