package cn.mayu.yugioh.sync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.sync.entity.MonsterEntity;

public interface MonsterRepository extends JpaRepository<MonsterEntity, Integer> {
}
