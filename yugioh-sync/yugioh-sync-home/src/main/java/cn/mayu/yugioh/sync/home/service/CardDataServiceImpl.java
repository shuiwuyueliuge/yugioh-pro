package cn.mayu.yugioh.sync.home.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mayu.yugioh.common.dto.sync.home.CardProto;
import cn.mayu.yugioh.common.dto.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.sync.home.async.DataTransformer;
import cn.mayu.yugioh.sync.home.entity.CardDataEntity;
import cn.mayu.yugioh.sync.home.entity.IncludeInfo;
import cn.mayu.yugioh.sync.home.repository.CardRepository;

@Service
public class CardDataServiceImpl implements CardDataService {
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private DataTransformer dataTransformer;

	@Override
	public void persistent(CardEntity cardEntity) throws Exception {
		CardDataEntity cardDataEntity = initCardDataEntity(cardEntity);
		CardDataEntity cardInfoEntity = cardRepository.findByHashId(cardDataEntity.getHashId()).block();
		if (cardInfoEntity == null) {
			cardRepository.save(cardDataEntity).subscribe(data -> dataTransformer.transformCardSave(cardEntity));
			return;
		}
		
		if (!cardDataEntity.getVersion().equals(cardInfoEntity.getVersion())) {
			cardDataEntity.setId(cardInfoEntity.getId());
			cardRepository.save(cardDataEntity).subscribe(data -> dataTransformer.transformCardUpdate(cardEntity));
		}
	}
	
	private CardDataEntity initCardDataEntity(CardEntity cardEntity) {
		CardDataEntity cardDataEntity = new CardDataEntity();
		BeanUtils.copyProperties(cardEntity, cardDataEntity);
		cardDataEntity.setModifyTime(LocalDateTime.now());
		cardDataEntity.setCreateTime(LocalDateTime.now());
		List<IncludeInfo> infos = cardEntity.getIncludeInfosList().stream().map(info -> initIncludeInfo(info)).collect(Collectors.toList());
		cardDataEntity.setIncludeInfos(infos);
		return cardDataEntity;
	}
	
	private IncludeInfo initIncludeInfo(CardProto.IncludeInfo info) {
		IncludeInfo includeInfo = new IncludeInfo();
		BeanUtils.copyProperties(info, includeInfo);
		return includeInfo;
	}
}
