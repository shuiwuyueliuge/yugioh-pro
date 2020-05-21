package cn.mayu.yugioh.sync.local.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import cn.mayu.yugioh.common.dto.sync.home.LimitDetilProto.LimitDetilEntity;
import cn.mayu.yugioh.sync.local.async.MqDataConsomer;

@EnableBinding(LimitInputStream.class)
public class LimitReceiver {
	
	@Autowired
	private MqDataConsomer dataConsomer;

	@StreamListener(LimitInputStream.LIMIT_SAVE_INPUT)
	public void receiveSave(byte[] data) throws Exception {
		LimitDetilEntity entity = LimitDetilEntity.parseFrom(data);
		dataConsomer.saveLimit(entity);
	}
}
