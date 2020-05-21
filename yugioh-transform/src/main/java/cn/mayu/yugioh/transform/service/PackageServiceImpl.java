package cn.mayu.yugioh.transform.service;

import cn.mayu.yugioh.common.dto.cardsource.PackageProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mayu.yugioh.common.dto.cardsource.PackageDetail;
import cn.mayu.yugioh.transform.domain.entity.PackageEntity;
import cn.mayu.yugioh.transform.repository.PackageRepository;

@Service
public class PackageServiceImpl implements PackageService {
	
	@Autowired
	private PackageRepository packageRepository;

	@Override
	@Transactional
	public Integer save(PackageProto.PackageDetail packageDetail) {
		PackageEntity packageEntity = packageRepository.findByName(packageDetail.getPackageName());
		if (packageEntity == null) packageEntity = new PackageEntity();
		packageEntity.setName(packageDetail.getPackageName());
		packageEntity.setShotName(packageDetail.getAbbreviate());
		packageEntity.setSellTime(packageDetail.getOfferingDate());
		packageEntity = packageRepository.save(packageEntity);
		return packageEntity.getId();
	}
}
