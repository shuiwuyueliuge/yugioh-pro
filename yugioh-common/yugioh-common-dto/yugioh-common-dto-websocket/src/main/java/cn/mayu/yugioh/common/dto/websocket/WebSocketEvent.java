package cn.mayu.yugioh.common.dto.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketEvent {

    private String msg;

    private Integer code;

    private Object data;

    private WebSocketSource source;

    public WebSocketEvent(WebSocketSource source, String msg, Integer code, Object data) {
        this.source = source;
        this.msg = msg;
        this.code = code;
        this.data = data;
    }
}
