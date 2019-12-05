package cn.mayu.yugioh.sync.local.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.sync.local.config.AsyncConfig;
import cn.mayu.yugioh.sync.local.config.CardIdThreadLocal;
import cn.mayu.yugioh.sync.local.service.CardService;

@Component
public class MqDataConsomer {

	@Autowired
	private CardService cardService;
	
	@Autowired
	private CardIdThreadLocal threadLocal;
	
	@Async(AsyncConfig.ASYNC_EXECUTOR_NAME)
	public void saveCard(CardEntity entity) {
		cardService.saveCardData(entity);
		threadLocal.remove();
	}
	
	@Async(AsyncConfig.ASYNC_EXECUTOR_NAME)
	public void updateCard(CardEntity entity) {
		cardService.updateCardData(entity);
		threadLocal.remove();
	}
}
