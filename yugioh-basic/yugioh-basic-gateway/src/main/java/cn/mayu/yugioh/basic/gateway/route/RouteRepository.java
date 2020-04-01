package cn.mayu.yugioh.basic.gateway.route;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<RouteEntity, Integer> {

	List<RouteEntity> findByStatus(Integer status);
}
