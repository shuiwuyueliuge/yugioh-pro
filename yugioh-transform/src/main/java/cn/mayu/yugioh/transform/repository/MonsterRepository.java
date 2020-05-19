package cn.mayu.yugioh.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.transform.domain.entity.MonsterEntity;

public interface MonsterRepository extends JpaRepository<MonsterEntity, Integer> {

	MonsterEntity findByNameAndPassword(String name, String password);
}
