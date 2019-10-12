package cn.mayu.yugioh.reptile.ourocg.manager;

import java.util.List;

public interface CardDataFindManager {

	String findMetaData(String url) throws Exception;
	
	List<String> findDetilData(String url) throws Exception;
}
