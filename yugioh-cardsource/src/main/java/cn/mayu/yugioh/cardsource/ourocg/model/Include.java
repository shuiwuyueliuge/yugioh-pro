package cn.mayu.yugioh.cardsource.ourocg.model;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
