package cn.mayu.yugioh.transform.domain.dto;

import cn.mayu.yugioh.common.dto.cardsource.CardDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

	private CardTypeDTO cardTypeDTO;
	
	private CardDetail cardDetail;
}