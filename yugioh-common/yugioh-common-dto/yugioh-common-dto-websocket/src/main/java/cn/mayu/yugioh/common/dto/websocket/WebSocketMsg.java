package cn.mayu.yugioh.common.dto.websocket;

import lombok.Data;

@Data
public class WebSocketMsg {

    private String msg;

    private Integer code;

    private String channelId;

    private String subscribe;

    private Object data;
}
