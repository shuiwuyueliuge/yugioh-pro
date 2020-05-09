package cn.mayu.yugioh.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.transform.entity.PackageInfoEntity;

public interface PackageInfoRepository extends JpaRepository<PackageInfoEntity, Integer> {
}
