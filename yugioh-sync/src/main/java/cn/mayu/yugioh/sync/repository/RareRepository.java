package cn.mayu.yugioh.sync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.sync.entity.RareEntity;

public interface RareRepository extends JpaRepository<RareEntity, Integer> {

	RareEntity findByShortName(String race);
}
