package cn.mayu.yugioh.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.transform.model.entity.RareEntity;

public interface RareRepository extends JpaRepository<RareEntity, Integer> {

	RareEntity findByShortName(String race);
}
