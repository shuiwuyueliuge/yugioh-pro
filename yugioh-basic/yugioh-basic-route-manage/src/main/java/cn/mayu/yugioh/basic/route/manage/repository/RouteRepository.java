package cn.mayu.yugioh.basic.route.manage.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.basic.route.manage.entity.RouteEntity;

public interface RouteRepository extends JpaRepository<RouteEntity, Integer> {

	List<RouteEntity> findByStatus(Integer status);
}
