package cn.mayu.yugioh.sync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.sync.entity.ImgEntity;

public interface ImgRepository extends JpaRepository<ImgEntity, Integer> {
}
