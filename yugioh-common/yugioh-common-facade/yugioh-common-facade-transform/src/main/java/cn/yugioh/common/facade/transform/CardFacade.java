package cn.yugioh.common.facade.transform;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "transform")
public interface CardFacade {

    @GetMapping("/card")
    CardDetail findByIdAndTypeVal(@RequestParam("id") Integer id,
                                  @RequestParam("typeVal") Integer typeVal);
}
