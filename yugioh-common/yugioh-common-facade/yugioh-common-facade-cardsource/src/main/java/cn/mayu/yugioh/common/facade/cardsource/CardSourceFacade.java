package cn.mayu.yugioh.common.facade.cardsource;

import cn.mayu.yugioh.common.dto.cardsource.SourceType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "card-source", contextId = "source", fallbackFactory = CardSourceFacadeFallbackFactory.class)
public interface CardSourceFacade {

    @GetMapping("/source/type")
    List<SourceType> getSourceType();
}
