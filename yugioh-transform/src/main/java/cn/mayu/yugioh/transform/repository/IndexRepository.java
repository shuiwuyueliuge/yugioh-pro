package cn.mayu.yugioh.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.transform.model.entity.IndexEntity;

public interface IndexRepository extends JpaRepository<IndexEntity, Integer> {
}
