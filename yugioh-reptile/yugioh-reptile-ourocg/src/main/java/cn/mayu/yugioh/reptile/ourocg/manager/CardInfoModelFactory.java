package cn.mayu.yugioh.reptile.ourocg.manager;

import static cn.mayu.yugioh.common.core.util.Base64Util.UrlImg2Base64;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.api.mongo.dto.CardDataMongoDTO.IncludeInfo;
import cn.mayu.yugioh.common.core.bean.AbstractModelFactory;
import cn.mayu.yugioh.reptile.ourocg.model.CardInfoEntity;
import cn.mayu.yugioh.reptile.ourocg.model.OurocgCard;

@Component
public class CardInfoModelFactory extends AbstractModelFactory<OurocgCard, CardInfoEntity> {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	protected CardInfoEntity doConvert(OurocgCard card) {
		CardInfoEntity entity = new CardInfoEntity();
		copyProperties(card, entity);
		entity.setImgUrl(generateImg(entity.getImgUrl()));
		entity.setIncludeInfos(includeInfoParser(card.getPackageDetil()));
		return entity;
	}
	
	private List<IncludeInfo> includeInfoParser(List<String> list) {
		return list.stream().filter(include -> include.indexOf(":") != -1).map(include -> initIncludeInfo(include)).collect(Collectors.toList());
	}

	private String generateImg(String url) {
		try {
			return UrlImg2Base64(url);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("download ourocg img file not found url [{}]", url);
			}

			return null;
		}
	}

	private IncludeInfo initIncludeInfo(String include) {
		String[] includes = include.split(":");
		IncludeInfo includeInfo = new IncludeInfo();
		includeInfo.setNumber(includes[3]);
		includeInfo.setPackName(includes[0]);
		includeInfo.setRace(includes[4]);
		includeInfo.setSellTime(includes[2]);
		includeInfo.setShortName(includes[1]);
		return includeInfo;
	}
}
