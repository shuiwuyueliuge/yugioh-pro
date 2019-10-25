package cn.mayu.yugioh.api.mongo.dto;

import java.util.List;
import lombok.Data;

@Data
public class CardLimitMongoDTO {
	
	private String name;

	private List<String> forbidden;
	
	private List<String> limited;
	
	private List<String> semiLimited;
}
