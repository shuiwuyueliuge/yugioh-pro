package cn.mayu.yugioh.sync.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.sync.local.entity.PackageInfoEntity;

public interface PackageInfoRepository extends JpaRepository<PackageInfoEntity, Integer> {
}
