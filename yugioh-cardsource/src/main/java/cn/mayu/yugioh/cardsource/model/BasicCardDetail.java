package cn.mayu.yugioh.cardsource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicCardDetail {

private String password;
	
	private String name;
	
	private String nameJa;
	
	private String nameEn;
	
	private Integer locale;
	
	private Integer typeVal;
	
	private String imgUrl;
	
	private Integer level;
	
	private String attribute;
	
	private String race;
	
	private String atk;
	
	private String def;
	
	private String pendL;
	
	private String pendR;
	
	private String link;
	
	private String nameNw;
	
	private String desc;
	
	private String descNw;
}
