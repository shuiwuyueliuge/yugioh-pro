package cn.mayu.yugioh.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.transform.entity.EffectEntity;

public interface EffectRepository extends JpaRepository<EffectEntity, Integer> {
}
