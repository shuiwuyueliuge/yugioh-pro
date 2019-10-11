package cn.mayu.yugioh.reptile.ourocg.model;

import lombok.Data;

@Data
public class OurocgCard {
	private int id;
	
	private String hashId;
	
	private String password;
	
	private String name;
	
	private String nameJa;
	
	private String nameEn;
	
	private int locale;
	
	private String typeSt;
	
	private int typeVal;
	
	private String imgUrl;
	
	private Integer level;
	
	private String attribute;
	
	private String race;
	
	private String atk;
	
	private String def;
	
	private Integer pendL;
	
	private Integer pendR;
	
	private Integer link;
	
	private String linkArrow;
	
	private String nameNw;
	
	private String desc;
	
	private String descNw;
	
	private String rare;
	
	private String packages;
	
	private String href;
}
