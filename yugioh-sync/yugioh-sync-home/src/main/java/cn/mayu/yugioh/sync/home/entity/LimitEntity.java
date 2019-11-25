package cn.mayu.yugioh.sync.home.entity;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "limit")
public class LimitEntity {
	
	private String name;

	private List<String> forbidden;
	
	private List<String> limited;
	
	private List<String> semiLimited;
	
	private LocalDateTime createTime;
	
	private LocalDateTime modifyTime;
}
