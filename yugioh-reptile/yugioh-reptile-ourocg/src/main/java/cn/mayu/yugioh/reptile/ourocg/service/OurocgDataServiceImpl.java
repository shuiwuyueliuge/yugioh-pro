package cn.mayu.yugioh.reptile.ourocg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static cn.mayu.yugioh.common.core.util.JsonUtil.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static cn.mayu.yugioh.common.core.util.DateUtil.*;
import static cn.mayu.yugioh.common.core.util.FileUtil.*;
import cn.mayu.yugioh.common.core.bean.ModelFactory;
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
	
	@Autowired
	private ModelFactory<OurocgCard, CardInfoEntity> modelFactory;

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
	
	private String genTodayFileName() {
		return String.format("%s%sCardData-%s", userDir(), File.separator, dayNow());
	}
	
	@Override
	public void findPackageDetil() throws Exception {
		try (BufferedReader reader = new BufferedReader(new FileReader(genTodayFileName()))) {
			String str = null;
			while((str = reader.readLine()) != null) {
				OurocgMetaData metaData = readValue(str, OurocgMetaData.class);
				metaData.getCards().stream().map(card -> modelEntityConvert(card)).forEach(entity -> persistent(entity));
			}
		}
	}

	private CardInfoEntity modelEntityConvert(OurocgCard card) {
		try {
			card.setPackageDetil(dataFindManager.findPackageData(card.getHref()));
		} catch (Exception e) {
			log.error("OurocgCard findPackageData error [{}]", e);
		}
		
		return modelFactory.convert(card);
	}
	
	private void persistent(CardInfoEntity entity) {
		// TODO 入库判断
		repository.save(entity).subscribe();
	}
}

//卡包信息 https://www.ourocg.cn/package/MP16-EN/wwSW1

//禁卡表

//卡包期号