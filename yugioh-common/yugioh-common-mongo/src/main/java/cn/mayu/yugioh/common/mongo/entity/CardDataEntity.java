package cn.mayu.yugioh.common.mongo.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "card")
public class CardDataEntity {

    private String id;
	
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
	
	private String pendL;
	
	private String pendR;
	
	private String link;
	
	private String linkArrow;
	
	private String nameNw;
	
	private String desc;
	
	private String descNw;
	
	private List<IncludeInfo> includeInfos;
	
	private String version;
	
	private String adjust;
	
	private LocalDateTime createTime;
	
	private LocalDateTime modifyTime;
}
