package cn.mayu.yugioh.api.mongo.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class CardDataMongoDTO {

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
	
	private Integer pendL;
	
	private Integer pendR;
	
	private Integer link;
	
	private String linkArrow;
	
	private String nameNw;
	
	private String desc;
	
	private String descNw;
	
	private List<IncludeInfo> includeInfos;
	
	private String version;
	
	private LocalDateTime ctrateTime;
	
	private LocalDateTime modifyTime;
	
	@Data
	public static class IncludeInfo {
		
		private String race;
		
		private String packName;
		
		private String number;
		
		private String shortName;
		
		private String sellTime;
	}
}
