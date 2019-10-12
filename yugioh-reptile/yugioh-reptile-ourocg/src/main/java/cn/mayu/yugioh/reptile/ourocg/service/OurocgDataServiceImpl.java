package cn.mayu.yugioh.reptile.ourocg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static cn.mayu.yugioh.common.core.util.JsonUtil.*;
import java.util.List;
import cn.mayu.yugioh.api.mongo.dto.CardDataMongoDTO.IncludeInfo;
import java.util.stream.Collectors;
import cn.mayu.yugioh.common.core.util.Base64Util;
import cn.mayu.yugioh.common.core.util.Md5Util;
import cn.mayu.yugioh.reptile.ourocg.manager.CardDataFindManager;
import cn.mayu.yugioh.reptile.ourocg.model.ArrRepository;
import cn.mayu.yugioh.reptile.ourocg.model.CardInfoEntity;
import cn.mayu.yugioh.reptile.ourocg.model.OurocgCard;
import cn.mayu.yugioh.reptile.ourocg.model.OurocgMetaData;

@Service
public class OurocgDataServiceImpl implements OurocgDataService {

	@Autowired
	private CardDataFindManager dataFindManager;
	
	@Autowired
	private ArrRepository repository;

	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public int findOurocgData(String url, int num) throws Exception {
		String data = dataFindManager.findMetaData(url);
		OurocgMetaData metaData = readValue(data, OurocgMetaData.class);
		List<CardInfoEntity> mongoDTOs = metaData.getCards().stream().map(card -> data2Entity(card)).filter(entity -> entity != null).collect(Collectors.toList());
		for (CardInfoEntity entity : mongoDTOs) {
			repository.save(entity).subscribe();
		}
		
		return metaData.getMeta().getTotalPage();
	}

	private CardInfoEntity data2Entity(OurocgCard card) {
		try {
			CardInfoEntity entity = new CardInfoEntity();
			BeanUtils.copyProperties(card, entity);
			List<IncludeInfo> includeInfos = dataFindManager.findDetilData(card.getHref()).stream().map(include -> initIncludeInfo(include)).collect(Collectors.toList());
			entity.setIncludeInfos(includeInfos);
			entity.setId(null);
			entity.setHashId(generateHashId(entity));
			entity.setImgUrl(generateImg(entity.getImgUrl()));
			return entity;
		} catch (Exception e) {
			log.error("OurocgCard to CardDataMongoDTO error [{}]", e);
			return null;
		}
	}
	
	private String generateHashId(CardInfoEntity entity) throws Exception {
		String hash = "";
		if (entity.getNameJa() != null) {
			hash = Md5Util.md5(entity.getNameJa());
		}
		
		if (entity.getNameEn() != null) {
			hash = Md5Util.md5(entity.getNameEn());
		}
		
		hash = Md5Util.md5(entity.getName());
		return hash.substring(0, 8);
	}
	
	private String generateImg(String url) {
		try {
			return Base64Util.UrlImg2Base64(url);
		} catch (Exception e) {
			//log.error("download ourocg img file not found url [{}]", url);
			return null;
		}
	}
	
	private IncludeInfo initIncludeInfo(String include) {
		String[] includes = include.split(":");
		IncludeInfo includeInfo = new IncludeInfo();
		includeInfo.setNumber(includes[3]);
		includeInfo.setPackName(includes[0]);
		includeInfo.setRace(includes[4]);
		includeInfo.setSellTime(includes[2]);
		includeInfo.setShortName(includes[1]);
		return includeInfo;
	}
}

//卡包信息 https://www.ourocg.cn/package/MP16-EN/wwSW1
//卡片元信息
//卡片具体信息
//找不到图片地址默认的是ygopro的图片（密码.jpg）
//mongo索引：卡片字符串md5,hashid,insert_time
// 日文名md5>英文名MD5>中文md5，8位hashid
// 卡片字符串md5验证版本
// 数据库查询按照hashid，比对md5，卡片入库时间（日），version默认为1
// 按照时间增量更新mysql，version：1新增，其他更新

//禁卡表

//卡包期号