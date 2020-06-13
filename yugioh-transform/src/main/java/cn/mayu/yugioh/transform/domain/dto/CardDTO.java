package cn.mayu.yugioh.transform.domain.dto;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.transform.CardProto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

	private CardTypeDTO cardTypeDTO;
	
	private CardProto.CardDetail cardDetail;
}
