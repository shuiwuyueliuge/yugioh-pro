package cn.mayu.yugioh.basic.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class Test implements GlobalFilter {
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		if (request.getURI().toString().indexOf("login") != -1) {
			return chain.filter(exchange);
		}
		
		String token = request.getQueryParams().getFirst("access_token");
		if (token == null || token.trim().equals("")) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		
		byte[] bytes = null;
		RedisConnection conn = getConnection();
		try {
			bytes = conn.get(("auth:" + token).getBytes());
		} finally {
			conn.close();
		}
		
		System.out.println(new String(bytes));
		if (bytes != null) {
			exchange.getResponse().setStatusCode(HttpStatus.OK);
			exchange.getResponse().getHeaders().add("Content-Type", "application/json");
			DataBuffer data = exchange.getResponse().bufferFactory().wrap(bytes);
			return exchange.getResponse().writeWith(Mono.just(data));
		} 
		
		return chain.filter(exchange);
	}
	
	private RedisConnection getConnection() {
		return redisConnectionFactory.getConnection();
	}
}

