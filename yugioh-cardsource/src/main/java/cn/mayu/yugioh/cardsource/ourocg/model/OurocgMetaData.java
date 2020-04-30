package cn.mayu.yugioh.cardsource.ourocg.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ourocg_packages")
public class OurocgMetaData {
	
	@Id
	private String id;

	private List<OurocgCard> cards;
	
	private Meta meta;
}
