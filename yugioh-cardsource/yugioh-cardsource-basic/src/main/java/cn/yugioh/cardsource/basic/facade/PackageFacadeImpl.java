package cn.yugioh.cardsource.basic.facade;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
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
    public void publishPackageDetail(@RequestBody PackageData packageData, @PathVariable("sourceType") Integer sourceType) {
        strategyContext.publishPackageDetail(packageData, sourceType);
    }

    @Override
    @GetMapping(value = "/package/{sourceType}")
    public List<PackageData> gainPackageList(@PathVariable("sourceType") Integer sourceType) {
        return strategyContext.gainPackageList(sourceType);
    }
}
