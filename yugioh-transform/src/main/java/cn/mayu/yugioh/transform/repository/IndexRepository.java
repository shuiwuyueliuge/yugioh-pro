package cn.mayu.yugioh.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.transform.entity.IndexEntity;

public interface IndexRepository extends JpaRepository<IndexEntity, Integer> {
}
