package cn.mayu.yugioh.common.dto.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSocketSource {

    private String channelId;

    private String subscribe;
}
