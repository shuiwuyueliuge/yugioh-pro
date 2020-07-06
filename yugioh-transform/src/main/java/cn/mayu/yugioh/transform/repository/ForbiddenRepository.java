package cn.mayu.yugioh.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.transform.model.entity.ForbiddenEntity;

public interface ForbiddenRepository extends JpaRepository<ForbiddenEntity, Integer> {

}
