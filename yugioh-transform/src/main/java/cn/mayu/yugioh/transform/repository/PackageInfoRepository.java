package cn.mayu.yugioh.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.transform.model.entity.PackageInfoEntity;

public interface PackageInfoRepository extends JpaRepository<PackageInfoEntity, Integer> {

	PackageInfoEntity findByRaceIdAndPackageIdAndCardIdAndTypeVal(Integer raceId, Integer packageId, Integer cardId, Integer typeVal);
}
