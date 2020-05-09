package cn.mayu.yugioh.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.transform.entity.AdjustEntity;

public interface AdjustRepository extends JpaRepository<AdjustEntity, Integer> {
}
