package cn.mayu.yugioh.cardsource.ourocg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncludeInfo {

	private String rare;
	
	private String packName;
	
	private String number;
	
	private String shortName;
	
	private String sellTime;
	
	private String href;
}
