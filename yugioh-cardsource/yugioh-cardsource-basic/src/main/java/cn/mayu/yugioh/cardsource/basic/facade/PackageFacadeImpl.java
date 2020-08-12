package cn.mayu.yugioh.cardsource.basic.facade;

import cn.mayu.yugioh.cardsource.basic.service.PackageService;
import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import cn.mayu.yugioh.common.facade.cardsource.PackageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PackageFacadeImpl implements PackageFacade {

    @Autowired
    private PackageService packageService;

    @Override
    @PostMapping("/package/detail/{sourceType}")
    public void publishPackageDetail(@RequestBody PackageData packageData, @PathVariable("sourceType") Integer sourceType) {
        packageService.publishPackageDetail(packageData, sourceType);
    }

    @Override
    @GetMapping("/package/{sourceType}")
    public List<PackageData> gainPackageList(@PathVariable("sourceType") Integer sourceType) {
        return packageService.gainPackageList(sourceType);
    }
}
