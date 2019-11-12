package cn.mayu.yugioh.sync.service;

public interface IndexService {

	void indexCache();
	
	Integer findByNameFromCache(Integer type, String name);
	
	String findByTypeIndexFromCache(Integer type, Integer index);
}
