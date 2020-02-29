package cn.mayu.yugioh.common.core.api;

public abstract class ResultModelFactory<T, D> {

	public T createResultModel(ResultCodeEnum resultCodeEnum, D data) {
		return doCreateFull(resultCodeEnum.code, resultCodeEnum.msg, data);
	}
	
	public T createResultModel(ResultCodeEnum resultCodeEnum) {
		return doCreateNoData(resultCodeEnum.code, resultCodeEnum.msg);
	}

	protected abstract T doCreateFull(int code, String msg, D data);
	
	protected abstract T doCreateNoData(int code, String msg);
}
