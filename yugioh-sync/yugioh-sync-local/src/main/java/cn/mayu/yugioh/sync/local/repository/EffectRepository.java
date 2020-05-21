package cn.mayu.yugioh.sync.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.sync.local.entity.EffectEntity;

public interface EffectRepository extends JpaRepository<EffectEntity, Integer> {
}
