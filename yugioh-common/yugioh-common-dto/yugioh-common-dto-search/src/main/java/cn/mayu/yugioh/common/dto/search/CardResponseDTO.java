package cn.mayu.yugioh.common.dto.search;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDTO {

    private Long count;

    private List<CardDetail> cardDetails;
}
