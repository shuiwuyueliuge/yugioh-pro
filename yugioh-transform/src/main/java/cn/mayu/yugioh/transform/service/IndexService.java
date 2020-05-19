package cn.mayu.yugioh.transform.service;

public interface IndexService {
	
	Integer findByNameFromCache(Integer type, String name);
	
	String findByTypeIndexFromCache(Integer type, Integer index);
}
