package cn.mayu.yugioh.cardsource.core.ourocg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitInfo {
	
	private String name;
	
	private List<String> forbidden;
	
	private List<String> limited;
	
	private List<String> semiLimited;

	private String publishTime;

	private String region;
}
