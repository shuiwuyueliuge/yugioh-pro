package cn.mayu.yugioh.sync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.sync.entity.PackageInfoEntity;

public interface PackageInfoRepository extends JpaRepository<PackageInfoEntity, Integer> {
}
