package cn.mayu.yugioh.sync.ourocg.service;

public interface TaskMemoryService {

	void markMemory(String key, Long value);
	
	Long checkMemory(String key);
	
	void increaseBy(String key);
	
	void remove(String key);
}
