package cn.mayu.yugioh.common.facade.transform;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(name = "transform", contextId = "card", fallbackFactory = HystrixClientFallbackFactory.class)
public interface CardFacade {

    @PostMapping("/card")
    List<CardDetail> findByIdAndTypeVal(@RequestBody List<CardDetail> details);
}
