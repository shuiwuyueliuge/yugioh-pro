package cn.mayu.yugioh.transform.domain.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageRareDTO {

	private List<String> rares;
	
	private Integer cardId; 
	
	private Integer packageId;
	
	private String serial;
	
	private Integer cardType;
}
