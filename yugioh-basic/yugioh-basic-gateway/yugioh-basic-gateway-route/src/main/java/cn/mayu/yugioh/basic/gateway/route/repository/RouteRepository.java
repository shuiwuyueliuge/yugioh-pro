package cn.mayu.yugioh.basic.gateway.route.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.basic.gateway.route.entity.RouteEntity;

public interface RouteRepository extends JpaRepository<RouteEntity, Integer> {

	List<RouteEntity> findByStatus(Integer status);
}
