package cn.mayu.yugioh.transform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.transform.domain.entity.TypeEntity;

public interface TypeRepository extends JpaRepository<TypeEntity, Integer> {

	List<TypeEntity> findByCardId(Integer cardId);
}
