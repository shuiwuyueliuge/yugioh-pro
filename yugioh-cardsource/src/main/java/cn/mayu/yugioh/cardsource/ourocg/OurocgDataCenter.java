package cn.mayu.yugioh.cardsource.ourocg;

import static cn.mayu.yugioh.common.core.util.JsonUtil.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.BeanUtils;
import cn.mayu.yugioh.cardsource.datacenter.LimitCenter;
import cn.mayu.yugioh.cardsource.datacenter.PackageCenter;
import cn.mayu.yugioh.cardsource.html.HtmlHandler;
import cn.mayu.yugioh.cardsource.ourocg.html.CardDataHtmlHandler;
import cn.mayu.yugioh.cardsource.ourocg.html.IncludeInfoHandler;
import cn.mayu.yugioh.cardsource.ourocg.html.LimitDataHandler;
import cn.mayu.yugioh.cardsource.ourocg.html.LimitListHandler;
import cn.mayu.yugioh.cardsource.ourocg.html.PackageListHandler;
import cn.mayu.yugioh.cardsource.ourocg.model.Include;
import cn.mayu.yugioh.cardsource.ourocg.model.IncludeInfo;
import cn.mayu.yugioh.cardsource.ourocg.model.LimitInfo;
import cn.mayu.yugioh.cardsource.ourocg.model.OurocgCard;
import cn.mayu.yugioh.cardsource.ourocg.model.OurocgMetaData;
import cn.mayu.yugioh.cardsource.ourocg.repository.OurocgLimitRepository;
import cn.mayu.yugioh.cardsource.ourocg.repository.OurocgIncludeRepository;
import cn.mayu.yugioh.cardsource.ourocg.repository.OurocgPackageRepository;
import cn.mayu.yugioh.common.dto.cardsource.CardDetail;
import cn.mayu.yugioh.common.dto.cardsource.LimitDetail;
import cn.mayu.yugioh.common.dto.cardsource.PackageDetail;
import static cn.mayu.yugioh.cardsource.ourocg.OurocgQueueGuardian.*;

public class OurocgDataCenter implements PackageCenter, LimitCenter {

	private String description;

	private HtmlHandler<String> cardDataTranslater;

	private HtmlHandler<Include> includeTranslater;

	private HtmlHandler<List<String>> packageListTranslater;
	
	private HtmlHandler<LimitInfo> limitDataTranslater;
	
	private HtmlHandler<List<String>> limitListTranslater;

	private OurocgPackageRepository ourocgRepository;

	private OurocgIncludeRepository includeRepository;
	
	private OurocgLimitRepository limitRepository; 

	public OurocgDataCenter(OurocgPackageRepository ourocgRepository, 
							OurocgIncludeRepository includeRepository, 
							OurocgLimitRepository limitRepository) {
		this.ourocgRepository = ourocgRepository;
		this.includeRepository = includeRepository;
		this.limitRepository = limitRepository;
		this.cardDataTranslater = new CardDataHtmlHandler();
		this.includeTranslater = new IncludeInfoHandler();
		this.packageListTranslater = new PackageListHandler();
		this.limitDataTranslater = new LimitDataHandler();
		this.limitListTranslater = new LimitListHandler();
		this.description = "https://www.ourocg.cn/package";
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public String description() {
		return description;
	}

	@Override
	public PackageDetail gainPackageDetail(String resources) {
		String data = cardDataTranslater.handle(resources);
		OurocgMetaData metaData = readToOurocgMetaData(data);
		PackageDetail packageDetail = new PackageDetail();
		ourocgRepository.save(metaData).subscribe();
		metaDataToPackageDetail(packageDetail, metaData);
		return packageDetail;
	}

	private OurocgMetaData readToOurocgMetaData(String data) {
		try {
			return readValueSnakeCase(data, OurocgMetaData.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void metaDataToPackageDetail(PackageDetail packageDetail, OurocgMetaData metaData) {
		String[] packageItml = metaData.getMeta().getMetaKw().split(",");
		packageDetail.setCardCount(metaData.getMeta().getCount());
		packageDetail.setPackageName(packageItml[1].trim());
		packageDetail.setAbbreviate(packageItml[0].trim());
		packageDetail.setCards(getCardDetailList(packageDetail, metaData.getCards()));
	}

	private List<CardDetail> getCardDetailList(PackageDetail packageDetail, List<OurocgCard> cards) {
		return cards.stream().map(ourocgCard -> {
			CardDetail cardDetail = new CardDetail();
			BeanUtils.copyProperties(ourocgCard, cardDetail);
			cardDetail.setTypeSt(Stream.of(ourocgCard.getTypeSt().split("\\|")).collect(Collectors.toList()));
			// 收录详情/调整 解析
			Include cd = includeTranslater.handle(ourocgCard.getHref());
			includeRepository.save(cd).subscribe(includeInfo -> 
				saveIncludeInfo(includeInfo, cardDetail, packageDetail));
			return cardDetail;
		}).collect(Collectors.toList());
	}

	private void saveIncludeInfo(Include cd, CardDetail cardDetail, PackageDetail packageDetail) {
		cardDetail.setAdjust(cd.getAdjust());
		for (IncludeInfo info : cd.getIncludeInfos()) {
			if (packageDetail.getPackageName().indexOf(info.getPackName()) == -1) {
				continue;
			}

			if (packageDetail.getPackageName().equals(info.getPackName())) {// 日文版卡包
				packageDetail.setOfferingDate(info.getSellTime());
				cardDetail.setSerial(info.getNumber());
				cardDetail.getRare().add(info.getRare());
			} else {// 英文版卡包重新放入队列
				syncAdd(info.getHref(), 0);
			}
		}
	}

	@Override
	public List<String> gainPackageList(String resources) {
		return packageListTranslater.handle(resources);
	}

	@Override
	public LimitDetail gainLimitDetail(String resources) {
		LimitInfo limitInfo = limitDataTranslater.handle(resources);
		limitRepository.save(limitInfo).subscribe();
		LimitDetail detail = new LimitDetail();
		BeanUtils.copyProperties(limitInfo, detail);
		return detail;
	}

	@Override
	public List<String> gainLimitList(String resources) {
		return limitListTranslater.handle(resources);
	}
}
