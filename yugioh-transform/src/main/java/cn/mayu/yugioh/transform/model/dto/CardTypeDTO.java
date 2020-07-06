package cn.mayu.yugioh.transform.model.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardTypeDTO {

	private Integer cardType;
	
	private Integer magicTrapType;
	
	private List<Integer> monsterType = new ArrayList<Integer>();
}
