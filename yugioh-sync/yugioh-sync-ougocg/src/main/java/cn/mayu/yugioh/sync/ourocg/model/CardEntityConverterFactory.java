package cn.mayu.yugioh.sync.ourocg.model;

import static cn.mayu.yugioh.common.core.util.Base64Util.UrlImg2Base64;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

import cn.mayu.yugioh.common.core.domain.AbstractDomainConverterFactory;
import cn.mayu.yugioh.common.core.util.Md5Util;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CardEntityConverterFactory extends AbstractDomainConverterFactory<OurocgCard, CardDataEntity> {

	@Override
	protected CardDataEntity doConvert(OurocgCard card) {
		CardDataEntity entity = new CardDataEntity();
		copyProperties(card, entity);
		entity.setId(null);
		entity.setVersion(generateVersion(card));
		entity.setImgUrl(generateImg(entity.getImgUrl()));
		entity.setModifyTime(LocalDateTime.now());
		entity.setCreateTime(LocalDateTime.now());
		return entity;
	}
	
	private String generateVersion(OurocgCard card) {
		StringBuilder builder = new StringBuilder();
		builder.append(card.getPassword())
		       .append(card.getName())
		       .append(card.getNameJa())
		       .append(card.getNameEn())
		       .append(card.getLocale())
		       .append(card.getTypeSt())
		       .append(card.getTypeVal())
		       .append(card.getImgUrl())
		       .append(card.getLevel())
		       .append(card.getAttribute())
		       .append(card.getRace())
		       .append(card.getAtk())
		       .append(card.getDef())
		       .append(card.getPendL())
		       .append(card.getPendR())
		       .append(card.getLink())
		       .append(card.getLinkArrow())
		       .append(card.getNameNw())
		       .append(card.getRare())
		       .append(card.getDesc())
		       .append(card.getDescNw())
		       .append(card.getAdjust())
		       .append(card.getPackages());
		String version = "";
		try {
			version = Md5Util.md5(builder.toString());
		} catch (Exception e) {
		}
		
		return version;
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
}
