package cn.mayu.yugioh.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.transform.entity.TypeEntity;

public interface TypeRepository extends JpaRepository<TypeEntity, Integer> {
}
