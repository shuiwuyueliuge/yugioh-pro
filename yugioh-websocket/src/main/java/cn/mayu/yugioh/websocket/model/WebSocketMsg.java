package cn.mayu.yugioh.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSocketMsg<T> {

    private String msg;

    private Integer code;

    private T data;
}
