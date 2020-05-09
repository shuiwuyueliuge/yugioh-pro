package cn.mayu.yugioh.transform.service;

public interface IndexService {

	void indexCache();
	
	Integer findByNameFromCache(Integer type, String name);
	
	String findByTypeIndexFromCache(Integer type, Integer index);
}
