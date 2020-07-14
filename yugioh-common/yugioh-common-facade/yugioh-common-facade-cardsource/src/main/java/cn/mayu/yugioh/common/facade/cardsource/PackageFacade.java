package cn.mayu.yugioh.common.facade.cardsource;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(name = "card-source")
public interface PackageFacade {

    @PostMapping("/package/detail/{sourceType}")
    void publishPackageDetail(@RequestBody PackageData packageData, @PathVariable("sourceType") Integer sourceType);

    @GetMapping("/package/{sourceType}")
    List<PackageData> gainPackageList(@PathVariable("sourceType") Integer sourceType);
}
