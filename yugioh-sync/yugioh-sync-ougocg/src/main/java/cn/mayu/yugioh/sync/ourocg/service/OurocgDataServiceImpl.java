package cn.mayu.yugioh.sync.ourocg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static cn.mayu.yugioh.common.core.util.JsonUtil.*;
import java.io.BufferedReader;
import java.io.FileReader;
import static cn.mayu.yugioh.common.core.util.FileUtil.*;

import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.sync.ourocg.manager.CardDataFindManager;
import cn.mayu.yugioh.sync.ourocg.model.CardDetil;
import cn.mayu.yugioh.sync.ourocg.model.OurocgCard;
import cn.mayu.yugioh.sync.ourocg.model.OurocgMetaData;
import cn.mayu.yugioh.sync.ourocg.repository.CardRepository;
import cn.mayu.yugioh.sync.ourocg.repository.LimitRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OurocgDataServiceImpl implements OurocgDataService {

	@Autowired
	private CardDataFindManager dataFindManager;
	
	@Autowired
	private CardRepository repository;
	
	@Autowired
	private LimitRepository limitRepository;
	
	@Autowired
	private DomainConverterFactory<OurocgCard, CardDataEntity> modelFactory;
	
	private static final String TOTAL_PAGE = "total_page";
	
	private static final String CUR_PAGE = "cur_page";
	
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
		try (BufferedReader reader = new BufferedReader(new FileReader(genTodayFileName()))) {
			String str = null;
			while((str = reader.readLine()) != null) {
				OurocgMetaData metaData = readValue(str, OurocgMetaData.class);
				metaData.getCards().stream().map(card -> modelToEntity(card)).forEach(entity -> persistent(entity));
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
	
	private CardDataEntity modelToEntity(OurocgCard card) {
		CardDetil cardDetil = findIncludeDetilAndAdjust(card.getHref());
		card.setIncludeInfos(cardDetil.getIncludeInfos());
		card.setAdjust(cardDetil.getAdjust());
		return modelFactory.convert(card);
	}
	
	private CardDetil findIncludeDetilAndAdjust(String href) {
		try {
			return dataFindManager.findIncludeInfo(href);
		} catch (Exception e) {
			log.error("OurocgCard findPackageData error [{}]", e);
			return null;
		}
	}
	
	private void persistent(CardDataEntity entity) {
		CardDataEntity cardInfoEntity = repository.findByHashId(entity.getHashId()).block();
		if (cardInfoEntity == null) {
			repository.save(entity).subscribe();
			return;
		}
		
		if (!entity.getVersion().equals(cardInfoEntity.getVersion())) {
			entity.setId(cardInfoEntity.getId());
			repository.save(entity).subscribe();// update
		}
	}

	@Override
	public void limitInfoSave(String latestUrl) throws Exception {
		dataFindManager.findLimitData(latestUrl).stream().forEach(data -> limitRepository.save(data).subscribe());
	}
}