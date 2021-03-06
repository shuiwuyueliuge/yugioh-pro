package cn.mayu.yugioh.cardsource.basic.facade;

import cn.mayu.yugioh.cardsource.basic.service.LimitService;
import cn.mayu.yugioh.common.dto.cardsource.LimitData;
import cn.mayu.yugioh.common.facade.cardsource.LimitFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class LimitFacadeImpl implements LimitFacade {

    @Autowired
    private LimitService limitService;

    @Override
    @PostMapping("/limit/{sourceType}")
    public void publishLimitDetail(@RequestBody LimitData limitData, @PathVariable("sourceType") Integer sourceType) {
        limitService.publishLimitDetail(limitData, sourceType);
    }

    @Override
    @GetMapping("/limit/{sourceType}")
    public List<LimitData> gainLimitList(@PathVariable("sourceType") Integer sourceType){
        return limitService.gainLimitList(sourceType);
    }
}
