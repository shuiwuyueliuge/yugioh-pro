package cn.mayu.yugioh.basic.gateway.daedalus.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    private static final String AUTH_HEADER = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String token = request.getQueryParams().getFirst("access_token");
        if (token == null || token.trim().equals("")) {
            return redirect(exchange, "http://127.0.0.1:9600/ilogin.html?redirect_to=" + request.getURI());
        }

        byte[] bytes = getRedisValue(("auth_json:" + token).getBytes());
        if (bytes == null) {
            return redirect(exchange, "http://127.0.0.1:9600/ilogin.html");
        }

        String authorizeInfo = new String(bytes);
        if (log.isDebugEnabled()) {
            log.debug("token:[{}] value:[{}]", token, authorizeInfo);
        }

        ServerHttpRequest newRequest = request.mutate().headers(data -> request.getHeaders()).header(AUTH_HEADER, authorizeInfo).build();
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).response(exchange.getResponse()).build();
        return chain.filter(newExchange);
    }

    private byte[] getRedisValue(byte[] key) {
        RedisConnection conn = getConnection();
        try {
            return conn.get(key);
        } finally {
            conn.close();
        }
    }

    private RedisConnection getConnection() {
        return redisConnectionFactory.getConnection();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    private Mono<Void> redirect(ServerWebExchange exchange, String url) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set("Location", url);
        return exchange.getResponse().setComplete();
    }

    private Mono<Void> return401(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer data = exchange.getResponse().bufferFactory().wrap("Access Denied".getBytes());
        return exchange.getResponse().writeWith(Mono.just(data));
    }
}