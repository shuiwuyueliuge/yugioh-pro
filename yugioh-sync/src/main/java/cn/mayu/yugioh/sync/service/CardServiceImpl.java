package cn.mayu.yugioh.sync.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static cn.mayu.yugioh.common.core.util.StringUtil.*;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.common.mongo.entity.IncludeInfo;
import cn.mayu.yugioh.sync.entity.AdjustEntity;
import cn.mayu.yugioh.sync.entity.EffectEntity;
import cn.mayu.yugioh.sync.entity.ImgEntity;
import cn.mayu.yugioh.sync.entity.PackageEntity;
import cn.mayu.yugioh.sync.entity.PackageInfoEntity;
import cn.mayu.yugioh.sync.entity.RareEntity;
import cn.mayu.yugioh.sync.repository.AdjustRepository;
import cn.mayu.yugioh.sync.repository.CardRepository;
import cn.mayu.yugioh.sync.repository.EffectRepository;
import cn.mayu.yugioh.sync.repository.ImgRepository;
import cn.mayu.yugioh.sync.repository.PackageInfoRepository;
import cn.mayu.yugioh.sync.repository.PackageRepository;
import cn.mayu.yugioh.sync.repository.RareRepository;
import cn.mayu.yugioh.sync.repository.SyncRecordRepository;
import reactor.core.publisher.Flux;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository repository;

	@Autowired
	private SyncRecordRepository record;
	
	@Autowired
	private AdjustRepository adjustRepository;
	
	@Autowired
	private EffectRepository effectRepository;
	
	@Autowired
	private ImgRepository imgRepository;
	
	@Autowired
	private RareRepository rareRepository;
	
	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private PackageInfoRepository packageInfoRepository;
	
	@Autowired
	private MonsterService monsterService;
	
	@Autowired
	private MagicTrapService magicTrapService;

	@Override
	public void saveCardData() throws Exception {
		Flux<CardDataEntity> dataFlux = repository.findByModifyTimeGreaterThanEqual(getLastSyncTime());
		if (dataFlux.count().block() == 0) {
			return;
		}

		dataFlux.subscribe(entity -> doSync(entity));
	}

	private LocalDateTime getLastSyncTime() {
		LocalDateTime lastSyncTime = record.findMaxCreateTime();
		return lastSyncTime == null ? LocalDateTime.of(1970, 1, 1, 1, 1) : lastSyncTime;
	}

	@Transactional
	private void doSync(CardDataEntity entity) {
		Integer cardId = entity.getTypeVal() == 1 ? monsterService.saveMonsterInfo(entity) : magicTrapService.saveMagicTrapInfo(entity);
		if (!entity.getAdjust().equals("")) {
			AdjustEntity adjust = new AdjustEntity();
			adjust.setCardId(cardId);
			adjust.setAdjust(entity.getAdjust());
			adjust.setTypeVal(entity.getTypeVal());
			adjustRepository.save(adjust);
		}
		
		EffectEntity effect = new EffectEntity();
		effect.setCardId(cardId);
		effect.setEffect(effectFormat(entity.getDesc()));
		effect.setEffectNw(effectFormat(entity.getDescNw()));
		effect.setTypeVal(entity.getTypeVal());
		effectRepository.save(effect);
		
		if (entity.getImgUrl() != null) {
			ImgEntity img = new ImgEntity();
			img.setCardId(cardId);
			img.setImg(entity.getImgUrl());
			img.setTypeVal(entity.getTypeVal());
			imgRepository.save(img);
		}
		
		if (entity.getIncludeInfos() != null && entity.getIncludeInfos().size() > 0) {
			for (IncludeInfo info : entity.getIncludeInfos()) {
				RareEntity savedRare = rareRepository.findByShortName(info.getRace());
				if (savedRare == null) {
					RareEntity rare = new RareEntity();
					rare.setShortName(info.getRace());
					savedRare = rareRepository.save(rare);
				}
				
				PackageEntity savedPackage = packageRepository.findByName(info.getPackName());
				if (savedPackage == null) {
					PackageEntity packageEntity = new PackageEntity();
					packageEntity.setName(info.getPackName());
					packageEntity.setSellTime(info.getSellTime());
					packageEntity.setShotName(info.getShortName());
					savedPackage = packageRepository.save(packageEntity);
				}
				
				PackageInfoEntity packageInfo = new PackageInfoEntity();
				packageInfo.setCardId(cardId);
				packageInfo.setNumber(info.getNumber());
				packageInfo.setPackageId(savedPackage.getId());
				packageInfo.setRaceId(savedRare.getId());
				packageInfo.setTypeVal(entity.getTypeVal());
				packageInfoRepository.save(packageInfo);
			}
		}
	}
}
