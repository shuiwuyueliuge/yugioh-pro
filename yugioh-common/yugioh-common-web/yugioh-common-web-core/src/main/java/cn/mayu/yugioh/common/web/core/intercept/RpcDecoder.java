package cn.mayu.yugioh.common.web.core.intercept;

import cn.mayu.yugioh.common.web.core.exception.RpcServiceException;
import cn.mayu.yugioh.common.web.core.intercept.web.ResultCodeEnum;
import cn.mayu.yugioh.common.web.core.intercept.web.RpcBody;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import java.io.IOException;
import java.lang.reflect.Type;

public class RpcDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        if (response.status() == 404 || response.status() == 204) {
            return Util.emptyValueOf(type);
        }

        if (response.body() == null) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RpcBody responseBody = objectMapper.readValue(response.body().asReader(Util.UTF_8), RpcBody.class);
        if (responseBody.getCode() == ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode()) {
            throw new RpcServiceException(responseBody.getStackTrace(), responseBody.getMsg());
        }

        return objectMapper.convertValue(responseBody.getData(), TypeFactory.defaultInstance().constructType(type));
    }
}
