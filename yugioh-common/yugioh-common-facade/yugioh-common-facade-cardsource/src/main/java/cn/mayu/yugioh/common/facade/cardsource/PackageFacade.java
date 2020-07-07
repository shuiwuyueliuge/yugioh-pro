package cn.mayu.yugioh.common.facade.cardsource;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(name = "card-source")
public interface PackageFacade {

    @PostMapping("/package")
    void publishPackageDetail(@RequestBody PackageData packageData, Integer sourceType);

    @GetMapping("/package")
    List<PackageData> gainPackageList(Integer sourceType);
}
