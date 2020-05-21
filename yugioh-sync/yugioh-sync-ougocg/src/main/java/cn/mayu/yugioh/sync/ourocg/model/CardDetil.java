package cn.mayu.yugioh.sync.ourocg.model;

import java.util.List;
import cn.mayu.yugioh.common.dto.sync.home.CardProto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardDetil {

	private List<CardProto.IncludeInfo> includeInfos;
	
	private String adjust;
}
