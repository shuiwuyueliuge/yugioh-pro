package cn.mayu.yugioh.sync.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.sync.local.entity.ImgEntity;

public interface ImgRepository extends JpaRepository<ImgEntity, Integer> {
}
