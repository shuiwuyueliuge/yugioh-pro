package cn.mayu.yugioh.sync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.sync.entity.IndexEntity;

public interface IndexRepository extends JpaRepository<IndexEntity, Integer> {
}
