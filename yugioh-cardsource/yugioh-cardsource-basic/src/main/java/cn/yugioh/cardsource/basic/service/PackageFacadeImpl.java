package cn.yugioh.cardsource.basic.service;

import cn.mayu.yugioh.common.facade.cardsource.PackageFacade;
import cn.yugioh.cardsource.basic.manager.CardSourceStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PackageFacadeImpl implements PackageFacade {

    @Autowired
    private CardSourceStrategyContext strategyContext;

    @Override
    @PostMapping(value = "/package/detail/{sourceType}")
    public void publishPackageDetail(List<String> packageUrls, Integer priority, Integer sourceType) {
        strategyContext.publishPackageDetail(packageUrls, priority, sourceType);
    }

    @Override
    @GetMapping(value = "/package/{sourceType}")
    public List<String> gainPackageList(@PathVariable("sourceType") Integer sourceType) {
        return strategyContext.gainPackageList(sourceType);
    }
}
