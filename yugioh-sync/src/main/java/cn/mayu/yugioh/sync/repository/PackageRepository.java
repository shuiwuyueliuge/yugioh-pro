package cn.mayu.yugioh.sync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.sync.entity.PackageEntity;

public interface PackageRepository extends JpaRepository<PackageEntity, Integer> {
}
