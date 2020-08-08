package cn.mayu.yugioh.common.dto.websocket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WebSocketResultEnum {

    SUCCESS(200, "success"),
    REPEAT_ANALYSE(500, "重复解析"),
    SYSTEM_ERROR(500, "服务器异常");

    final int code;

    final String msg;
}
