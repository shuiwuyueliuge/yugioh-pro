package cn.mayu.yugioh.cardsource.ourocg;

import static cn.mayu.yugioh.common.core.util.JsonUtil.readValue;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.BeanUtils;
import cn.mayu.yugioh.cardsource.datacenter.PackageCenter;
import cn.mayu.yugioh.cardsource.html.HtmlHandler;
import cn.mayu.yugioh.cardsource.model.CardDetail;
import cn.mayu.yugioh.cardsource.model.PackageDetail;
import cn.mayu.yugioh.cardsource.ourocg.html.CardDataHtmlHandler;
import cn.mayu.yugioh.cardsource.ourocg.html.IncludeInfoHandler;
import cn.mayu.yugioh.cardsource.ourocg.html.PackageListHandler;
import cn.mayu.yugioh.cardsource.ourocg.model.Include;
import cn.mayu.yugioh.cardsource.ourocg.model.IncludeInfo;
import cn.mayu.yugioh.cardsource.ourocg.model.OurocgMetaData;
import cn.mayu.yugioh.cardsource.repository.IncludeRepository;
import cn.mayu.yugioh.cardsource.repository.OurocgRepository;

public class OurocgDataCenter implements PackageCenter {
	
	private String description;
	
	private HtmlHandler<String> cardDataTranslater;
	
	private HtmlHandler<Include> includeTranslater;
	
	private HtmlHandler<List<String>> packageListTranslater;
	
	private OurocgRepository ourocgRepository;
	
	private IncludeRepository includeRepository;
	
	public OurocgDataCenter(OurocgRepository ourocgRepository, IncludeRepository includeRepository) {
		this.ourocgRepository = ourocgRepository;
		this.includeRepository = includeRepository;
		this.cardDataTranslater = new CardDataHtmlHandler();
		this.includeTranslater = new IncludeInfoHandler();
		this.packageListTranslater = new PackageListHandler();
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
	public PackageDetail gainPackageDetail(String resources) throws Exception {
		String data = cardDataTranslater.handle(resources);
		OurocgMetaData metaData = readValue(data, OurocgMetaData.class);
		ourocgRepository.save(metaData).subscribe(System.out::println);
		String[] packageItml = metaData.getMeta().getMetaKw().split(",");
		PackageDetail packageDetail = new PackageDetail();
		packageDetail.setCardCount(metaData.getMeta().getCount());
		packageDetail.setPackageName(packageItml[1].trim());
		packageDetail.setAbbreviate(packageItml[0].trim());
		List<CardDetail> cardDetails = metaData.getCards().stream().map(ourocgCard -> {
			CardDetail cardDetail = new CardDetail();
			BeanUtils.copyProperties(ourocgCard, cardDetail);
			cardDetail.setTypeSt(Stream.of(ourocgCard.getTypeSt().split("\\|")).collect(Collectors.toList()));
			// href解析
			try {
				Include cd = includeTranslater.handle(ourocgCard.getHref());
				includeRepository.save(cd).subscribe(System.out::println);
				cardDetail.setAdjust(cd.getAdjust());
				for (IncludeInfo info : cd.getIncludeInfos()) {
					if (packageItml[1].trim().indexOf(info.getPackName()) == -1) {
						continue;
					}
					
					if (packageItml[1].trim().equals(info.getPackName())) {// 日文版
						packageDetail.setOfferingDate(info.getSellTime());
						cardDetail.setSerial(info.getNumber());
						cardDetail.getRare().add(info.getRare());
					} else {// 英文版
						OurocgQueueGuardian.syncAdd("https://www.ourocg.cn" + info.getHref(), 0);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return cardDetail;
		}).collect(Collectors.toList());
		packageDetail.setCards(cardDetails);
		return packageDetail;
	}

	@Override
	public List<String> gainPackageList(String resources) throws Exception {
		return packageListTranslater.handle(resources);
	}
}
