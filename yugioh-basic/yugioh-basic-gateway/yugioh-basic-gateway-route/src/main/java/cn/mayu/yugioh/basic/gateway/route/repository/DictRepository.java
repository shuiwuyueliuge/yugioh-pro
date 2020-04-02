package cn.mayu.yugioh.basic.gateway.route.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.basic.gateway.route.entity.DictEntity;

public interface DictRepository extends JpaRepository<DictEntity, Integer> {

}
