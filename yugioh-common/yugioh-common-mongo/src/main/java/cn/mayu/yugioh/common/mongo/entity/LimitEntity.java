package cn.mayu.yugioh.common.mongo.entity;

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
}
