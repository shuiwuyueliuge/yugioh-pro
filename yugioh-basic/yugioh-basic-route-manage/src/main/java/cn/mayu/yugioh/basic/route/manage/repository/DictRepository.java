package cn.mayu.yugioh.basic.route.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.basic.route.manage.entity.DictEntity;

public interface DictRepository extends JpaRepository<DictEntity, Integer> {

}
