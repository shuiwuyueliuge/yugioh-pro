package cn.mayu.yugioh.transform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mayu.yugioh.transform.domain.dto.PackageRareDTO;
import cn.mayu.yugioh.transform.domain.entity.PackageInfoEntity;
import cn.mayu.yugioh.transform.domain.entity.RareEntity;
import cn.mayu.yugioh.transform.repository.PackageInfoRepository;
import cn.mayu.yugioh.transform.repository.RareRepository;

@Service
public class RareServiceImpl implements RareService {

	@Autowired
	private RareRepository rareRepository;

	@Autowired
	private PackageInfoRepository packageInfoRepository;

	@Override
	@Transactional
	public void save(PackageRareDTO packageRareDto) {
		for (String rare : packageRareDto.getRares()) {
			RareEntity rareEntity = rareRepository.findByShortName(rare);
			if (rareEntity == null) rareEntity = new RareEntity();
			rareEntity.setShortName(rare);
			rareEntity = rareRepository.save(rareEntity);
			// t_card_package
			PackageInfoEntity packageInfo = packageInfoRepository.findByRaceIdAndPackageIdAndCardIdAndTypeVal(
					rareEntity.getId(), packageRareDto.getPackageId(), packageRareDto.getCardId(),
					packageRareDto.getCardType());
			if (packageInfo == null) packageInfo = new PackageInfoEntity();
			packageInfo.setRaceId(rareEntity.getId());
			packageInfo.setPackageId(packageRareDto.getPackageId());
			packageInfo.setCardId(packageRareDto.getCardId());
			packageInfo.setTypeVal(packageRareDto.getCardType());
			packageInfo.setNumber(packageRareDto.getSerial());
			packageInfoRepository.save(packageInfo);
		}
	}
}
