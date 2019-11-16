package cn.mayu.yugioh.sync.ourocg.model;

import java.util.List;
import cn.mayu.yugioh.common.mongo.entity.IncludeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardDetil {

	private List<IncludeInfo> includeInfos;
	
	private String adjust;
}
