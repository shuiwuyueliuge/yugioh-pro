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
@Document(collection = "ourocg_include")
public class Include {
	
	@Id
	private String id;
	
	private String cardName;

	private List<IncludeInfo> includeInfos;
	
	private String adjust;
	
	private LocalDateTime createTime = LocalDateTime.now();
}
