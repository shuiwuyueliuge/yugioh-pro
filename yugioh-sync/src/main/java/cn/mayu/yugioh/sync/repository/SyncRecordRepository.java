package cn.mayu.yugioh.sync.repository;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import cn.mayu.yugioh.sync.entity.SyncRecordEntity;

public interface SyncRecordRepository extends JpaRepository<SyncRecordEntity, Integer> {

	@Query(value = "SELECT create_time FROM t_sync_record ORDER BY create_time DESC LIMIT 1", nativeQuery = true)
	LocalDateTime findMaxCreateTime();
}
