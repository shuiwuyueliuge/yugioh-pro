package cn.mayu.yugioh.cardsource.core.ourocg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ourocg_limit")
public class LimitInfo {
	
	@Id
	private String id;
	
	private String name;
	
	private List<String> forbidden;
	
	private List<String> limited;
	
	private List<String> semiLimited;

	private String publishTime;

	private String region;
	
	private LocalDateTime createTime = LocalDateTime.now();
}
