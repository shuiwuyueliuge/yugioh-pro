package cn.mayu.yugioh.common.dto.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageWebSocketDTO {

    private String channelId;

    private Integer progress;

    private String packageName;
}
