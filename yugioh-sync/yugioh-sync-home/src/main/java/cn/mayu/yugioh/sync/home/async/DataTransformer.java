package cn.mayu.yugioh.sync.home.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.dto.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.common.dto.sync.home.LimitDetilProto.LimitDetilEntity;
import cn.mayu.yugioh.sync.home.config.AsyncConfig;
import cn.mayu.yugioh.sync.home.stream.CardSender;
import cn.mayu.yugioh.sync.home.stream.LimitSender;

@Component
public class DataTransformer {
	
	@Autowired
	private CardSender cardSender;
	
	@Autowired
	private LimitSender limitSender;

	@Async(AsyncConfig.ASYNC_EXECUTOR_NAME)
	public void transformCardUpdate(CardEntity entity) {
		cardSender.update(entity);
	}
	
	@Async(AsyncConfig.ASYNC_EXECUTOR_NAME)
	public void transformCardSave(CardEntity entity) {
		cardSender.save(entity);
	}
	
	@Async(AsyncConfig.ASYNC_EXECUTOR_NAME)
	public void transformLimitSave(LimitDetilEntity entity) {
		limitSender.save(entity);
	}
}
