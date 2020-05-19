package cn.mayu.yugioh.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.transform.domain.entity.PackageEntity;

public interface PackageRepository extends JpaRepository<PackageEntity, Integer> {

	PackageEntity findByName(String packName);
}
