package cn.mayu.yugioh.common.dto.sync.home;

import cn.mayu.yugioh.common.core.api.ResultModelFactory;
import cn.mayu.yugioh.common.dto.sync.home.ResultProto.ResultEntity;

public class ResultProtoFactory extends ResultModelFactory<ResultProto.ResultEntity, Void> {

	@Override
	protected ResultEntity doCreateNoData(int code, String msg) {
		return build(code, msg);
	}

	@Override
	protected ResultEntity doCreateFull(int code, String msg, Void data) {
		return build(code, msg);
	}
	
	private ResultEntity build(int code, String msg) {
		return ResultEntity.getDefaultInstance().toBuilder().setCode(code).setMsg(msg).build();
	}
}
