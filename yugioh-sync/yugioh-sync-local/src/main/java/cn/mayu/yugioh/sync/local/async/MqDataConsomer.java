package cn.mayu.yugioh.sync.local.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.dto.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.common.dto.sync.home.LimitDetilProto.LimitDetilEntity;
import cn.mayu.yugioh.sync.local.config.AsyncConfig;
import cn.mayu.yugioh.sync.local.config.CardIdThreadLocal;
import cn.mayu.yugioh.sync.local.service.CardService;
import cn.mayu.yugioh.sync.local.service.ForbiddenService;

@Component
public class MqDataConsomer {

	@Autowired
	private CardService cardService;
	
	@Autowired
	private ForbiddenService forbiddenService;
	
	@Autowired
	private CardIdThreadLocal threadLocal;
	
	@Async(AsyncConfig.ASYNC_EXECUTOR_NAME)
	public void saveCard(CardEntity entity) {
		try {
			cardService.saveCardData(entity);
		} finally {
			threadLocal.remove();
		}
	}
	
	@Async(AsyncConfig.ASYNC_EXECUTOR_NAME)
	public void updateCard(CardEntity entity) {
		try {
			cardService.updateCardData(entity);
		} finally {
			threadLocal.remove();
		}
	}
	
	@Async(AsyncConfig.ASYNC_EXECUTOR_NAME)
	public void saveLimit(LimitDetilEntity entity) {
		forbiddenService.saveLimitCard(entity);
	}
}
