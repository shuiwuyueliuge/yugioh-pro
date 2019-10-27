package cn.mayu.yugioh.common.core.factory;

import org.springframework.beans.BeanUtils;

public abstract class AbstractModelFactory<S, T> implements ModelFactory<S, T> {

	@Override
	public T convert(S source) {
		return doConvert(source);
	}
	
	protected void copyProperties(S source, T target) {
		BeanUtils.copyProperties(source, target);
	}
	
	protected abstract T doConvert(S source);
}
