package cn.mayu.yugioh.common.facade.cardsource;

import cn.mayu.yugioh.common.dto.cardsource.LimitData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "card-source", contextId = "limit")
public interface LimitFacade {

    @PostMapping("/limit/{sourceType}")
    void publishLimitDetail(@RequestBody LimitData limitData, @PathVariable("sourceType") Integer sourceType);

    @GetMapping("/limit/{sourceType}")
    List<LimitData> gainLimitList(@PathVariable("sourceType") Integer sourceType);
}

