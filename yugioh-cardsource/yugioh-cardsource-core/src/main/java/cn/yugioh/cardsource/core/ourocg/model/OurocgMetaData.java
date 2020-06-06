package cn.yugioh.cardsource.core.ourocg.model;

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
@Document(collection = "ourocg_packages")
public class OurocgMetaData {
	
	@Id
	private String id;

	private List<OurocgCard> cards;
	
	private Meta meta;
	
	private LocalDateTime createTime = LocalDateTime.now();
}
