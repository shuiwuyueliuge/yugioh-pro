package cn.mayu.yugioh.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.transform.domain.entity.AdjustEntity;

public interface AdjustRepository extends JpaRepository<AdjustEntity, Integer> {

	AdjustEntity findByCardIdAndTypeVal(Integer cardId, Integer typeVal);
}
