package cn.mayu.yugioh.sync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.sync.entity.MagicTrapEntity;

public interface MagicTrapRepository extends JpaRepository<MagicTrapEntity, Integer> {
}
