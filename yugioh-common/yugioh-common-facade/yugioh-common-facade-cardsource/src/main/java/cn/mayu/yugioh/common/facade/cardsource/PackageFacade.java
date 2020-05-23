package cn.mayu.yugioh.common.facade.cardsource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "sync-home")
public interface PackageFacade {

    @GetMapping(value = "/card")
    void publishPackageDetail(List<String> packageUrls, Integer priority, Integer sourceType);

    @GetMapping(value = "/card")
    List<String> gainPackageList(Integer sourceType);
}
