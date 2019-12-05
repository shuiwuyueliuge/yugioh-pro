package cn.mayu.yugioh.sync.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.sync.local.entity.MagicTrapEntity;

public interface MagicTrapRepository extends JpaRepository<MagicTrapEntity, Integer> {

	MagicTrapEntity findByNameAndPassword(String name, String password);
}