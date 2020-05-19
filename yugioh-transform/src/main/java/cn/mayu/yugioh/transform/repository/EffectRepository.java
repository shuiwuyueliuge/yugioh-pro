package cn.mayu.yugioh.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.transform.domain.entity.EffectEntity;

public interface EffectRepository extends JpaRepository<EffectEntity, Integer> {

	EffectEntity findByCardIdAndTypeVal(Integer cardId, Integer typeVal);
}
