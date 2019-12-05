package cn.mayu.yugioh.sync.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.sync.local.entity.ForbiddenEntity;

public interface ForbiddenRepository extends JpaRepository<ForbiddenEntity, Integer> {

}