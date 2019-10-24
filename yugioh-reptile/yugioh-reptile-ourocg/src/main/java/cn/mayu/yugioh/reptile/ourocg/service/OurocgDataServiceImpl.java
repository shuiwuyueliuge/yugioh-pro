package cn.mayu.yugioh.reptile.ourocg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static cn.mayu.yugioh.common.core.util.JsonUtil.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
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
	
	private static final String TOTAL_PAGE = "total_page";
	
	private static final String CUR_PAGE = "cur_page";
	
	private static StringBuffer stringBuffer;
	
	static {
		stringBuffer = new StringBuffer();
	}

	@Override
	public boolean ourocgDataInFile(String url) throws Exception {
		String data = dataFindManager.findMetaData(url);
		long total = readTree(data, TOTAL_PAGE);
		long curr = readTree(data, CUR_PAGE);
		inFile(curr, total, data);
		return curr >= total ? false : true;
	}
	
	@Override
	public void packageDetilSave() throws Exception {
		try (BufferedReader reader = new BufferedReader(new FileReader(genTodayFileName()))) {
			String str = null;
			while((str = reader.readLine()) != null) {
				OurocgMetaData metaData = readValue(str, OurocgMetaData.class);
				metaData.getCards().stream().map(card -> modelEntityConvert(card)).forEach(entity -> persistent(entity));
			}
		}
	}
	
	private void inFile(long currentPage, long total, String data) throws Exception {
		stringBuffer.append(String.format("%s%s", data, "\r\n"));
		if (currentPage % 100 == 0 || currentPage == total) {
			saveInFile(genTodayFileName(), stringBuffer.toString());
			stringBuffer = new StringBuffer();
		}
	}
	
	private CardInfoEntity modelEntityConvert(OurocgCard card) {
		card.setPackageDetil(findPackageDetil(card.getHref()));
		return modelFactory.convert(card);
	}
	
	private List<String> findPackageDetil(String href) {
		try {
			return dataFindManager.findPackageData(href);
		} catch (Exception e) {
			log.error("OurocgCard findPackageData error [{}]", e);
			return null;
		}
	}
	
	private void persistent(CardInfoEntity entity) {
		CardInfoEntity cardInfoEntity = repository.findByHashId(entity.getHashId()).block();
		if (cardInfoEntity == null) {
			repository.save(entity).subscribe();
			return;
		}
		
		if (!entity.getVersion().equals(cardInfoEntity.getVersion())) {
			entity.setId(cardInfoEntity.getId());
			repository.save(entity).subscribe();// update
		}
	}
}

//卡包信息 https://www.ourocg.cn/package/MP16-EN/wwSW1

//禁卡表

//卡包期号