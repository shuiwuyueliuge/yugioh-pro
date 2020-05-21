package cn.mayu.yugioh.sync.ourocg.model;

import static cn.mayu.yugioh.common.core.util.Base64Util.UrlImg2Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.domain.AbstractDomainConverterFactory;
import cn.mayu.yugioh.common.core.util.Md5Util;
import cn.mayu.yugioh.common.dto.sync.home.CardProto;
import cn.mayu.yugioh.sync.ourocg.manager.CardDataFindManager;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CardEntityConverterFactory extends AbstractDomainConverterFactory<OurocgCard, CardProto.CardEntity> {

	@Autowired
	private CardDataFindManager dataFindManager;
	
	@Override
	protected CardProto.CardEntity doConvert(OurocgCard card) {
		CardProto.CardEntity.Builder builder = CardProto.CardEntity.getDefaultInstance().toBuilder();
		CardDetil detil = findIncludeDetilAndAdjust(card.getHref());
		builder.setVersion(generateVersion(card, detil.getAdjust()))
		       .setImgUrl(generateImg(card.getImgUrl()))
		       .setId(card.getId())
		       .setHashId(card.getHashId())
		       .setPassword(card.getPassword())
		       .setName(card.getName())
		       .setNameJa(card.getNameJa())
		       .setNameEn(card.getNameEn())
		       .setLocale(card.getLocale())
		       .setTypeSt(card.getTypeSt())
		       .setTypeVal(card.getTypeVal())
		       .setLevel(card.getLevel())
		       .setAttribute(card.getAttribute())
		       .setRace(card.getRace())
		       .setAtk(card.getAtk())
		       .setDef(card.getDef())
		       .setPendL(card.getPendL())
		       .setPendR(card.getPendR())
		       .setLink(card.getLink())
		       .setLinkArrow(card.getLinkArrow())
		       .setNameNw(card.getNameNw())
		       .setDesc(card.getDesc())
		       .setDescNw(card.getDescNw())
		       .setAdjust(detil.getAdjust())
		       .addAllIncludeInfos(detil.getIncludeInfos());
		return builder.build();
	}
	
	private String generateVersion(OurocgCard card, String adjust) {
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
		       .append(adjust)
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

			return "";
		}
	}
	
	private CardDetil findIncludeDetilAndAdjust(String href) {
		try {
			return dataFindManager.findIncludeInfo(href);
		} catch (Exception e) {
			log.error("OurocgCard findPackageData error [{}]", e);
			return null;
		}
	}
}
