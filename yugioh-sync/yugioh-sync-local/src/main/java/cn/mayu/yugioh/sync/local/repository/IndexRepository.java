package cn.mayu.yugioh.sync.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.sync.local.entity.IndexEntity;

public interface IndexRepository extends JpaRepository<IndexEntity, Integer> {
}
