package cn.mayu.yugioh.security.core.imagecode;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "yugioh.security.image")
public class ImageCodeProperty {

    private String imageCodeUri = "/code/image";

    private String imageCodeKeyParam = "imageKey";

    private String imageCodeValueParam = "imageCode";
}
