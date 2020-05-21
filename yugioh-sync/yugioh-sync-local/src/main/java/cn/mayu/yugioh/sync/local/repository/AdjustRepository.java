package cn.mayu.yugioh.sync.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.sync.local.entity.AdjustEntity;

public interface AdjustRepository extends JpaRepository<AdjustEntity, Integer> {
}
