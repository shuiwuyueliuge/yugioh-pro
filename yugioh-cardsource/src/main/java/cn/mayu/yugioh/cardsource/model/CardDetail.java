package cn.mayu.yugioh.cardsource.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CardDetail extends BasicCardDetail {
	
	private List<String> typeSt;
	
	private List<String> linkArrow;
	
	private List<String> rare;
	
	private List<String> packages;
}