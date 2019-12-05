package cn.mayu.yugioh.sync.local.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.facade.sync.home.CardProto.IncludeInfo;
import cn.mayu.yugioh.sync.local.config.CardIdThreadLocal;
import cn.mayu.yugioh.sync.local.entity.PackageEntity;
import cn.mayu.yugioh.sync.local.entity.PackageInfoEntity;
import cn.mayu.yugioh.sync.local.entity.RareEntity;
import cn.mayu.yugioh.sync.local.repository.PackageInfoRepository;
import cn.mayu.yugioh.sync.local.repository.PackageRepository;
import cn.mayu.yugioh.sync.local.repository.RareRepository;

@Service
public class PackageServiceImpl implements PackageService {
	
	@Autowired
	private RareRepository rareRepository;
	
	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private PackageInfoRepository packageInfoRepository;
	
	@Autowired
	private CardIdThreadLocal threadLocal;

	@Override
	@Transactional
	public void savePackageInfo(CardEntity entity) {
		List<IncludeInfo> list = entity.getIncludeInfosList();
		if (list == null || list.size() <= 0) {
			return;
		}
		
		for (IncludeInfo info : list) {
			int savedRareId = saveRare(info);
			int savedPackageId = savePackage(info);
			savePackageDetil(info, savedRareId, savedPackageId, threadLocal.getId(), entity.getTypeVal());
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
