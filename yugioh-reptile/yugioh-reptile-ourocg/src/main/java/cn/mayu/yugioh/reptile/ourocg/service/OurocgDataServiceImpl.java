package cn.mayu.yugioh.reptile.ourocg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mayu.yugioh.common.core.util.JsonUtil;
import cn.mayu.yugioh.reptile.ourocg.manager.CardMetaDataFindManager;
import cn.mayu.yugioh.reptile.ourocg.model.OurocgMetaData;

@Service
public class OurocgDataServiceImpl implements OurocgDataService {

	@Autowired
	private CardMetaDataFindManager dataFindManager;

	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public int findOurocgData(String url, int num) throws Exception {
		String data = dataFindManager.doExec(url);
		OurocgMetaData metaData = JsonUtil.readValue(data, OurocgMetaData.class);
		
		
		return metaData.getMeta().getTotalPage();
	}
}

//卡片元信息
//卡片具体信息

//禁卡表

//卡包期号
//卡片密码为-，日文名md5>英文名MD5>中文名md5