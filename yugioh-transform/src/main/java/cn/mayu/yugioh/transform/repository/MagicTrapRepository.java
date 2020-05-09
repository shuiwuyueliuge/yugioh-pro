package cn.mayu.yugioh.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.transform.entity.MagicTrapEntity;

public interface MagicTrapRepository extends JpaRepository<MagicTrapEntity, Integer> {

	MagicTrapEntity findByNameAndPassword(String name, String password);
}
