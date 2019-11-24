package cn.mayu.yugioh.sync.ourocg.model;

import static cn.mayu.yugioh.common.core.util.Base64Util.UrlImg2Base64;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.domain.AbstractDomainConverterFactory;
import cn.mayu.yugioh.common.core.util.Md5Util;
import cn.mayu.yugioh.facade.sync.home.CardProto;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CardProtoConverterFactory extends AbstractDomainConverterFactory<OurocgCard, CardProto.CardEntity> {

	@Override
	protected CardProto.CardEntity doConvert(OurocgCard card) {
		CardProto.CardEntity.Builder builder = CardProto.CardEntity.getDefaultInstance().toBuilder();
		builder.setVersion(generateVersion(card));
		builder.setImgUrl(generateImg(card.getImgUrl()));
		CardProto.CardEntity entity = builder.build();
		copyProperties(card, entity);
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
