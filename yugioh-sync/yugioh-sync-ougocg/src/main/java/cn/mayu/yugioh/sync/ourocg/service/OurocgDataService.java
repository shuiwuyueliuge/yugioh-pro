package cn.mayu.yugioh.sync.ourocg.service;

public interface OurocgDataService {

	boolean ourocgDataInFile(String url) throws Exception;
	
	void packageDetilSave() throws Exception;

	void limitInfoSave(String latestUrl) throws Exception;
}
