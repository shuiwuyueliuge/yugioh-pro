package cn.mayu.yugioh.sync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.sync.entity.TypeEntity;

public interface TypeRepository extends JpaRepository<TypeEntity, Integer> {
}
