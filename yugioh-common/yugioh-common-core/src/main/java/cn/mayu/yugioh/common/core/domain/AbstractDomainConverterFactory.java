package cn.mayu.yugioh.common.core.domain;

import org.springframework.beans.BeanUtils;

public abstract class AbstractDomainConverterFactory<S, T> implements DomainConverterFactory<S, T> {

	@Override
	public T convert(S source) {
		return doConvert(source);
	}
	
	@Override
	public T apply(S source) {
		return doConvert(source);
	}
	
	protected void copyProperties(S source, T target) {
		BeanUtils.copyProperties(source, target);
	}
	
	protected void copyProperties(S source, T target, String... ignoreProperties) {
		BeanUtils.copyProperties(source, target, ignoreProperties);
	}
	
	protected abstract T doConvert(S source);
}
