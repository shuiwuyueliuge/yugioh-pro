package cn.mayu.yugioh.cardsource.core.ourocg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Include {
	
	private String cardName;

	private List<IncludeInfo> includeInfos;
	
	private String adjust;
}
