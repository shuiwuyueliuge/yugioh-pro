package cn.mayu.yugioh.sync.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.sync.local.entity.MonsterEntity;

public interface MonsterRepository extends JpaRepository<MonsterEntity, Integer> {

	MonsterEntity findByNameAndPassword(String name, String password);
}
