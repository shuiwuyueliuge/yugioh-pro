package cn.mayu.yugioh.sync.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.sync.local.entity.PackageEntity;

public interface PackageRepository extends JpaRepository<PackageEntity, Integer> {

	PackageEntity findByName(String packName);
}
