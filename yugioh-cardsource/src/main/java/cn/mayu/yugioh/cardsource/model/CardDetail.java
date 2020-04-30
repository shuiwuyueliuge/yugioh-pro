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
	
	// 卡片类型
	private List<String> typeSt;
	
	// 连接方向
	private List<String> linkArrow;
	
	// 罕贵度
	private List<String> rare;
	
	// wiki
	private String wiki;
}