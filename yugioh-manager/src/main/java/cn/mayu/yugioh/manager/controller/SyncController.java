package cn.mayu.yugioh.manager.controller;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import cn.mayu.yugioh.common.dto.cardsource.SourceType;
import cn.mayu.yugioh.manager.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class SyncController {

    @Autowired
    private SyncService syncService;

    @GetMapping("/sync/package/{sourceType}")
    public List<PackageData> gainPackageList(@PathVariable("sourceType") Integer sourceType) {
        return syncService.gainPackageList(sourceType);
    }

    @PostMapping("/sync/package/{sourceType}")
    public void publishPackageDetail(@RequestBody PackageData packageData, @PathVariable("sourceType") Integer sourceType) {
        syncService.publishPackageDetail(packageData, sourceType);
    }

    @GetMapping("/sync/source")
    public List<SourceType> getSourceType() {
        return syncService.getSourceType();
    }
}
