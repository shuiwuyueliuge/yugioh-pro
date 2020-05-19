package cn.mayu.yugioh.transform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.transform.domain.entity.LinkEntity;

public interface LinkRepository extends JpaRepository<LinkEntity, Integer> {

	List<LinkEntity> findByCardId(Integer cardId);
}
