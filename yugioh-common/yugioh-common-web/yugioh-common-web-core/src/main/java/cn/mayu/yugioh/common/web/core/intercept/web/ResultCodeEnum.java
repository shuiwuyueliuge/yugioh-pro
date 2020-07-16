package cn.mayu.yugioh.common.web.core.intercept.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {


    SUCCESS(200, "Operation is Successful"),

    FAILURE(400, "Biz Exception"),

    UN_AUTHORIZED(401, "Request Unauthorized"),

    NOT_FOUND(404, "404 Not Found"),

    MSG_NOT_READABLE(400, "Message Can't be Read"),

    METHOD_NOT_SUPPORTED(405, "Method Not Supported"),

    MEDIA_TYPE_NOT_SUPPORTED(415, "Media Type Not Supported"),

    REQ_REJECT(403, "Request Rejected"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    PARAM_MISS(400, "Missing Required Parameter"),

    PARAM_TYPE_ERROR(400, "Parameter Type Mismatch"),

    PARAM_BIND_ERROR(400, "Parameter Binding Error"),

    PARAM_VALID_ERROR(400, "Parameter Validation Error");

    final int code;

    final String msg;
}