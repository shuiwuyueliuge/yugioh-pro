package cn.mayu.yugioh.transform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.transform.entity.LinkEntity;

public interface LinkRepository extends JpaRepository<LinkEntity, Integer> {
}
