package cn.mayu.yugioh.reptile.ourocg.manager;

import java.util.List;
import cn.mayu.yugioh.reptile.ourocg.model.CardLimitEntity;

public interface CardDataFindManager {

	String findMetaData(String url) throws Exception;
	
	List<String> findPackageData(String url) throws Exception;

	List<CardLimitEntity> findLimitCard(String latestUrl) throws Exception;
}
