package cn.mayu.yugioh.manager.controller;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import cn.mayu.yugioh.common.facade.cardsource.PackageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PackageController {

    @Autowired
    private PackageFacade packageFacade;

    @GetMapping("/package/{sourceType}")
    public List<PackageData> gainPackageList(@PathVariable("sourceType") Integer sourceType) {
        return packageFacade.gainPackageList(sourceType);
    }
}
