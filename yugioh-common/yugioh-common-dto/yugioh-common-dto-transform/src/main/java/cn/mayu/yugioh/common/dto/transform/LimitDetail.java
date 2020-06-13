package cn.mayu.yugioh.common.dto.transform;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitDetail {
	
	private String name;
	
	private List<String> forbidden;
	
	private List<String> limited;
	
	private List<String> semiLimited;

	private String publishTime;

	private String region;
}
