package cn.mayu.yugioh.reptile.ourocg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static cn.mayu.yugioh.common.core.util.JsonUtil.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import cn.mayu.yugioh.api.mongo.dto.CardDataMongoDTO.IncludeInfo;
import java.util.stream.Collectors;
import cn.mayu.yugioh.common.core.util.Base64Util;
import static cn.mayu.yugioh.common.core.util.DateUtil.*;
import static cn.mayu.yugioh.common.core.util.FileUtil.*;
import cn.mayu.yugioh.reptile.ourocg.manager.CardDataFindManager;
import cn.mayu.yugioh.reptile.ourocg.model.OurocgCardRepository;
import cn.mayu.yugioh.reptile.ourocg.model.CardInfoEntity;
import cn.mayu.yugioh.reptile.ourocg.model.OurocgCard;
import cn.mayu.yugioh.reptile.ourocg.model.OurocgMetaData;

@Service
public class OurocgDataServiceImpl implements OurocgDataService {

	@Autowired
	private CardDataFindManager dataFindManager;
	
	@Autowired
	private OurocgCardRepository repository;

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private static StringBuffer stringBuffer;
	
	static {
		stringBuffer = new StringBuffer();
	}

	@Override
	public boolean findOurocgData(String url) throws Exception {
		String data = dataFindManager.findMetaData(url);
		long total = readTree(data, "total_page");
		long curr = readTree(data, "cur_page");
		saveData(curr, total, data);
		return curr >= total ? false : true;
	}
	
	private void saveData(long currentPage, long total, String data) throws Exception {
		stringBuffer.append(String.format("%s%s", data, "\r\n"));
		if (currentPage % 100 == 0 || currentPage == total) {
			saveInFile(genTodayFileName(), stringBuffer.toString());
			stringBuffer = new StringBuffer();
		}
	}
	
	@Override
	public void findPackageDetil() throws Exception {
		try (BufferedReader reader = new BufferedReader(new FileReader(genTodayFileName()))) {
			String str = null;
			while((str = reader.readLine()) != null) {
				readValue(str, OurocgMetaData.class).getCards().stream().map(card -> data2Entity(card)).forEach(entity -> repository.save(entity).subscribe());
			}
		}
	}
	
	private String genTodayFileName() {
		return String.format("%s%sCardData-%s", userDir(), File.separator, dayNow());
	}

	private CardInfoEntity data2Entity(OurocgCard card) {
		CardInfoEntity entity = new CardInfoEntity();
		BeanUtils.copyProperties(card, entity);
		entity.setImgUrl(generateImg(entity.getImgUrl()));
		try {
			List<IncludeInfo> includeInfos = dataFindManager.findPackageData(card.getHref()).stream().map(include -> initIncludeInfo(include)).collect(Collectors.toList());
			entity.setIncludeInfos(includeInfos);
		} catch (Exception e) {
			log.error("OurocgCard to CardDataMongoDTO error [{}]", e);
		}
		
		return entity;
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

//禁卡表

//卡包期号