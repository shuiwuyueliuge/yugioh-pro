package cn.mayu.yugioh.common.core.api;

public class RestResultModelFactory<D> extends ResultModelFactory<RestResult<D>, D> {

	@Override
	protected RestResult<D> doCreateFull(int code, String msg, D data) {
		return new RestResult<D>(code, msg, data);
	}

	@Override
	protected RestResult<D> doCreateNoData(int code, String msg) {
		return new RestResult<D>(code, msg, null);
	}
}
