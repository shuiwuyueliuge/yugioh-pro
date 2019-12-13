package cn.mayu.yugioh.sync.home.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "sync_record")
public class SyncRecordEntity {
	
	@Id
	private String id;
	
	private Object data;
	
	private Integer operate;//0 update, 1 save
}
