package cn.mayu.yugioh.sync.local.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CardIdThreadLocal {

	private static final ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<Integer>();

	public void setId(Integer cardId) {
		THREAD_LOCAL.set(cardId);
	}
	
	public Integer getId() {
		return THREAD_LOCAL.get();
	}
	
	public void remove() {
		THREAD_LOCAL.remove();
	}
}
