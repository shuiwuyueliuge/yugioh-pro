package cn.mayu.yugioh.sync.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.sync.local.entity.TypeEntity;

public interface TypeRepository extends JpaRepository<TypeEntity, Integer> {
}
