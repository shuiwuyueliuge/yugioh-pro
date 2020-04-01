package cn.mayu.yugioh.basic.gateway;

import java.util.Map.Entry;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import cn.mayu.yugioh.common.core.util.JwtUtil;
import cn.mayu.yugioh.common.core.util.RSAUtil;
import reactor.core.publisher.Mono;

//@Component
public class Test implements GlobalFilter, Ordered {
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	@Autowired
	RsaProperty property;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		if (request.getURI().toString().indexOf("login") != -1) {
			return chain.filter(exchange);
		}
		
		String token = request.getQueryParams().getFirst("access_token");
		if (token == null || token.trim().equals("")) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			exchange.getResponse().getHeaders().add("Content-Type", "application/json");
			DataBuffer data = exchange.getResponse().bufferFactory().wrap("没有权限".getBytes());
			return exchange.getResponse().writeWith(Mono.just(data));
		}
		
		try {
			Set<Entry<String, Object>> ss = JwtUtil.parse(RSAUtil.restorePublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm4irSNcR7CSSfXconxL4g4M4j34wTWdTv93ocMn4VmdB7rCBU/BlxXtBUf/cgLIgQhQrAPszSZSmxiEXCOkGPr4aQBQuPgmNIR95Dhbzw/ZN0BnecAt3ZfkkDBHv8kH3kR/jYGTdwrxKeDgXGljNsTRhbjuASxPG/Z6gU1yRPCsgc2r8NYnztWGcDWqaobqjG3/yzFmusoAboyV7asIpo4yk378LmonDNwxnOOTb2Peg5PeelwfOwJPbftK1VOOt18zA0cchw6dHUzq9NlB8clps/VdBap9BxU3/0YoFXRIc18nyzrWo2BcY2KQqX//AJC3OAfrfDmo+BGK8E0mp8wIDAQAB"), token);
		    ss.stream().forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		byte[] bytes = null;
		RedisConnection conn = getConnection();
		try {
			bytes = conn.get(("auth_json:" + token).getBytes());
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

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}
}

