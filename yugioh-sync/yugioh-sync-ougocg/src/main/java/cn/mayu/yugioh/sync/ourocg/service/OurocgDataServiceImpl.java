package cn.mayu.yugioh.sync.ourocg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static cn.mayu.yugioh.common.core.util.JsonUtil.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static cn.mayu.yugioh.common.core.util.FileUtil.*;
import cn.mayu.yugioh.common.core.api.ResultCodeEnum;
import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.dto.sync.home.CardProto;
import cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity;
import cn.mayu.yugioh.common.dto.sync.home.SaveResultProto.SaveResultEntity;
import cn.mayu.yugioh.facade.sync.home.SyncHomeService;
import cn.mayu.yugioh.sync.ourocg.manager.CardDataFindManager;
import cn.mayu.yugioh.sync.ourocg.model.OurocgCard;
import cn.mayu.yugioh.sync.ourocg.model.OurocgMetaData;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OurocgDataServiceImpl implements OurocgDataService {

	@Autowired
	private CardDataFindManager dataFindManager;
	
	@Autowired
	DomainConverterFactory<OurocgCard, CardProto.CardEntity> factory;
	
	@Autowired
	private SyncHomeService syncHomeService;
	
	@Autowired
	private TaskMemoryService memoryService;
	
	private static final String SKIP_SIZE_KEY = "%s:card:skip";
	
	private static final String TOTAL_PAGE = "total_page";
	
	private static final String CUR_PAGE = "cur_page";
	
	private static final String LINE_FEED = "\r\n";
	
	private static StringBuffer stringBuffer;
	
	static {
		stringBuffer = new StringBuffer();
	}

	@Override
	public boolean ourocgDataInFile(String url) throws Exception {
		String data = dataFindManager.findCardData(url);
		long total = readTree(data, TOTAL_PAGE);
		long curr = readTree(data, CUR_PAGE);
		inFile(curr, total, data);
		return curr >= total ? false : true;
	}
	
	@Override
	public void packageDetilSave() throws Exception {
		File file = new File(genTodayFileName());
		String key = String.format(SKIP_SIZE_KEY, file.getName());
		long skip = memoryService.checkMemory(key);
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			reader.skip(skip);
			String str = null;
			while((str = reader.readLine()) != null) {
				skip = skip + (str + LINE_FEED).length();
				memoryService.markMemory(key, skip);
				OurocgMetaData metaData = readValue(str, OurocgMetaData.class);
				metaData.getCards().stream().forEach(card -> persistentCard(card));
			}
		}
	}
	
	private void inFile(long currentPage, long total, String data) throws Exception {
		stringBuffer.append(String.format("%s%s", data, LINE_FEED));
		if (currentPage % 100 == 0 || currentPage == total) {
			saveInFile(genTodayFileName(), stringBuffer.toString());
			stringBuffer = new StringBuffer();
		}
	}
	
	private void persistentCard(OurocgCard card) {
		CardProto.CardEntity entity = factory.convert(card);
		SaveResultEntity result = syncHomeService.saveCardInMongo(entity);
		if (result.getCode() != ResultCodeEnum.SUCCESS.getCode()) {
			log.error("persistent card error, card: [{}], code: [{}], msg: [{}]", card, result.getCode(), result.getMsg());
		}
	}

	@Override
	public void limitInfoSave(String latestUrl) throws Exception {
		dataFindManager.findLimitData(latestUrl).stream().forEach(data -> persistentLimit(data));
	}
	
	private void persistentLimit(LimitEntity data) {
		SaveResultEntity result = syncHomeService.saveLimitInMongo(data);
		if (result.getCode() != ResultCodeEnum.SUCCESS.getCode()) {
			log.error("persistent card error, limit: [{}], msg: [{}]", data.getName(), result.getCode(), result.getMsg());
		}
	}
}