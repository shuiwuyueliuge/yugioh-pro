package cn.mayu.yugioh.common.web.core.intercept.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcBody {

    private StackTraceElement[] stackTrace;

    private String msg;

    private String message;

    private Object data;

    private Integer code;
}
