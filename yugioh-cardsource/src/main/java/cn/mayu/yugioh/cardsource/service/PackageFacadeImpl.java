package cn.mayu.yugioh.cardsource.service;

import cn.mayu.yugioh.cardsource.manager.CardSourceStrategyContext;
import cn.mayu.yugioh.common.facade.cardsource.PackageFacade;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PackageFacadeImpl
        //implements PackageFacade
{

    @Autowired
    private CardSourceStrategyContext strategyContext;

    //@Override
    @PostMapping(value = "/package/detail/{sourceType}")
    public void publishPackageDetail(@RequestBody AAA aaa, @PathVariable("sourceType") Integer sourceType) {
        strategyContext.publishPackageDetail(aaa.getPackageUrls(), aaa.getPriority(), sourceType);
    }

   // @Override
    @GetMapping(value = "/package/{sourceType}")
    public List<String> gainPackageList(@PathVariable("sourceType") Integer sourceType) {
        return strategyContext.gainPackageList(sourceType);
    }

    @Data
    public static class AAA {
        List<String> packageUrls;
        Integer priority = 0;
    }
}
