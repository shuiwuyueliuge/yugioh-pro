package cn.mayu.yugioh.sync.ourocg.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import cn.mayu.yugioh.sync.ourocg.config.OurocgCardDeserializerConfig;
import lombok.Data;

@Data
@JsonDeserialize(using = OurocgCardDeserializerConfig.class)
public class OurocgCard {
	
	private String id;
	
	private String hashId;
	
	private String password;
	
	private String name;
	
	private String nameJa;
	
	private String nameEn;
	
	private Integer locale;
	
	private String typeSt;
	
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
	
	private String linkArrow;
	
	private String nameNw;
	
	private String desc;
	
	private String descNw;
	
	private String rare;
	
	private String packages;
	
	private String href;

}
