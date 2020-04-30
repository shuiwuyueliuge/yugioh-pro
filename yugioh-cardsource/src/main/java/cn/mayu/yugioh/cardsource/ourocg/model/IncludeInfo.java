package cn.mayu.yugioh.cardsource.ourocg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncludeInfo {

	private String race;
	
	private String packName;
	
	private String number;
	
	private String shortName;
	
	private String sellTime;
}
