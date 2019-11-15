package cn.mayu.yugioh.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.common.mongo.entity.IncludeInfo;
import cn.mayu.yugioh.sync.entity.PackageEntity;
import cn.mayu.yugioh.sync.entity.PackageInfoEntity;
import cn.mayu.yugioh.sync.entity.RareEntity;
import cn.mayu.yugioh.sync.repository.PackageInfoRepository;
import cn.mayu.yugioh.sync.repository.PackageRepository;
import cn.mayu.yugioh.sync.repository.RareRepository;

@Service
public class PackageServiceImpl implements PackageService {
	
	@Autowired
	private RareRepository rareRepository;
	
	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private PackageInfoRepository packageInfoRepository;

	@Override
	@Transactional
	public void savePackageInfo(CardDataEntity entity) {
		if (entity.getIncludeInfos() == null || entity.getIncludeInfos().size() <= 0) {
			return;
		}
		
		for (IncludeInfo info : entity.getIncludeInfos()) {
			int savedRareId = saveRare(info);
			int savedPackageId = savePackage(info);
			savePackageDetil(info, savedRareId, savedPackageId, entity.getId(), entity.getTypeVal());
		}
	}
	
	@Transactional
	private int saveRare(IncludeInfo info) {
		RareEntity savedRare = rareRepository.findByShortName(info.getRace());
		if (savedRare == null) {
			RareEntity rare = new RareEntity();
			rare.setShortName(info.getRace());
			savedRare = rareRepository.save(rare);
		}
		
		return savedRare.getId();
	}
	
	@Transactional
	private int savePackage(IncludeInfo info) {
		PackageEntity savedPackage = packageRepository.findByName(info.getPackName());
		if (savedPackage == null) {
			PackageEntity packageEntity = new PackageEntity();
			packageEntity.setName(info.getPackName());
			packageEntity.setSellTime(info.getSellTime());
			packageEntity.setShotName(info.getShortName());
			savedPackage = packageRepository.save(packageEntity);
		}
		
		return savedPackage.getId();
	}
	
	@Transactional
	private void savePackageDetil(IncludeInfo info, int savedRareId, int savedPackageId, int cardId, int typeVal) {
		PackageInfoEntity packageInfo = new PackageInfoEntity();
		packageInfo.setCardId(cardId);
		packageInfo.setNumber(info.getNumber());
		packageInfo.setPackageId(savedPackageId);
		packageInfo.setRaceId(savedRareId);
		packageInfo.setTypeVal(typeVal);
		packageInfoRepository.save(packageInfo);
	}
}
