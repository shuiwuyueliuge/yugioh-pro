package cn.mayu.yugioh.cardsource.ourocg.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import cn.mayu.yugioh.cardsource.ourocg.OurocgCardDeserializer;
import cn.mayu.yugioh.common.dto.cardsource.BasicCardDetail;
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

	private String type;

	private String state;

	private String createdAt;

	private String updatedAt;

	private String deletedAt;

	private String rdType;
	
	private String effectKW;
	
	private String iCardType;
	
	private String sCardType;
	
	private String keyword;
	
	private String locale;

	private String href;
	
	private String typeVal;
}
