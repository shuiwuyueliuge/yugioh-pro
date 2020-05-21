package cn.mayu.yugioh.basic.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.basic.config.model.entity.PropertiesEntity;

public interface PropertiesRepository extends JpaRepository<PropertiesEntity, Integer> {

}
