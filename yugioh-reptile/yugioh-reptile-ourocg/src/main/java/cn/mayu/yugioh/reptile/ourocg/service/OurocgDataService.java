package cn.mayu.yugioh.reptile.ourocg.service;

public interface OurocgDataService {

	boolean ourocgDataInFile(String url) throws Exception;
	
	void packageDetilSave() throws Exception;
}
