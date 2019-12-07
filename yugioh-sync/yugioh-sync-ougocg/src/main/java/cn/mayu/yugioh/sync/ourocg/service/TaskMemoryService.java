package cn.mayu.yugioh.sync.ourocg.service;

public interface TaskMemoryService {

	void markMemory(String key, String value);
	
	String checkMemory(String key);
	
	void increaseBy(String key);
}
