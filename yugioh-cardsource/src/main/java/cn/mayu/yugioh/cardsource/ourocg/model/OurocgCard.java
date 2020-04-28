package cn.mayu.yugioh.cardsource.ourocg.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import cn.mayu.yugioh.cardsource.model.BasicCardDetail;
import cn.mayu.yugioh.cardsource.ourocg.OurocgCardDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = OurocgCardDeserializer.class)
public class OurocgCard extends BasicCardDetail {
	
	private String id;
	
	private String hashId;
	
	private String typeSt;
	
	private String linkArrow;
	
	private String rare;
	
	private String packages;
	
	private String href;
}
