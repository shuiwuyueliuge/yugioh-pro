package cn.mayu.yugioh.basic.gateway.daedalus.route;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.common.core.util.JsonUtil;
import lombok.Data;
import reactor.core.publisher.Mono;

//@Service
public class DynamicRouteServiceImpl implements DynamicRouteService {

	@Autowired
	private RouteDefinitionWriter routeDefinitionWriter;

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	private ApplicationEventPublisher publisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
		newThread(this).start();
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setDaemon(true);
		return thread;
	}

	@Override
	public void run() {
		while (true) {
			loadRouteTask();
			try {
				TimeUnit.SECONDS.sleep(10L);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void loadRouteTask() {
		byte[] routeKey = "gateway:routes".getBytes();
		RedisConnection conn = redisConnectionFactory.getConnection();
		try {
			byte[] res = conn.rPop(routeKey);
			while (res != null) {
				RouteDefinitionWrapper wrapper = RouteDefinitionWrapper.readValue(res);
				RouteDefinition data = wrapper.getRouteDefinition();
				if (wrapper.getStatus() == 1) {
					add(data);
					continue;
				}

				if (wrapper.getStatus() == 0) {
					update(data);
					continue;
				}

				if (wrapper.getStatus() == -1) {
					delete(data.getId());
				}

				res = conn.rPop(routeKey);
			}
		} finally {
			conn.close();
		}
	}

	private void add(RouteDefinition definition) {
		routeDefinitionWriter.save(Mono.just(definition)).subscribe();
		publishEvent();
	}

	private void update(RouteDefinition definition) {
		routeDefinitionWriter.delete(Mono.just(definition.getId()));
		routeDefinitionWriter.save(Mono.just(definition)).subscribe();
		publishEvent();
	}

	private void delete(String id) {
		this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
		publishEvent();
	}

	private void publishEvent() {
		this.publisher.publishEvent(new RefreshRoutesEvent(this));
	}

	@Data
	public static class RouteDefinitionWrapper {

		private RouteDefinition routeDefinition;

		private int status;

		public static RouteDefinitionWrapper readValue(byte[] res) {
			try {
				return JsonUtil.readValue(res, RouteDefinitionWrapper.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}