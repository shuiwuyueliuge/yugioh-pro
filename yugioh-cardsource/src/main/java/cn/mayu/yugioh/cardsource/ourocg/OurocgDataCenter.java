package cn.mayu.yugioh.cardsource.ourocg;

import static cn.mayu.yugioh.common.core.util.JsonUtil.readValue;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.cardsource.datacenter.PackageCenter;
import cn.mayu.yugioh.cardsource.model.CardDetail;
import cn.mayu.yugioh.cardsource.model.PackageDetail;
import cn.mayu.yugioh.cardsource.ourocg.html.CardDataHtmlHandler;
import cn.mayu.yugioh.cardsource.ourocg.html.IncludeInfoHandler;
import cn.mayu.yugioh.cardsource.ourocg.model.CardDetil;
import cn.mayu.yugioh.cardsource.ourocg.model.OurocgMetaData;
import cn.mayu.yugioh.cardsource.repository.OurocgRepository;
import cn.mayu.yugioh.common.core.html.HtmlHandler;

@Component
public class OurocgDataCenter implements PackageCenter {
	
	private String description;
	
	private HtmlHandler<String> cardDataTranslater;
	
	private HtmlHandler<CardDetil> includeTranslater;
	
	@Autowired
	private OurocgRepository ourocgRepository;
	
	public OurocgDataCenter() {
		this.cardDataTranslater = new CardDataHtmlHandler();
		this.includeTranslater = new IncludeInfoHandler();
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
		//ourocgRepository.save(metaData).subscribe();
		
		List<CardDetail> cardDetails = metaData.getCards().stream().map(ourocgCard -> {
			CardDetail cardDetail = new CardDetail();
			BeanUtils.copyProperties(ourocgCard, cardDetail);
			cardDetail.setTypeSt(Stream.of(ourocgCard.getTypeSt().split("\\|")).collect(Collectors.toList()));
			// href解析
			try {
				CardDetil cd = includeTranslater.handle(ourocgCard.getHref());
				System.out.println(cd);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cardDetail;
		}).collect(Collectors.toList());
		
		String[] packageItml = metaData.getMeta().getMetaKw().split(",");
		PackageDetail packageDetail = new PackageDetail();
		packageDetail.setCards(cardDetails);
		packageDetail.setCardCount(metaData.getMeta().getCount());
		packageDetail.setPackageName(packageItml[1].trim());
		packageDetail.setAbbreviate(packageItml[0].trim());
		return packageDetail;
	}
	
	public static void main(String[] args) throws Exception {
		//new OurocgCardDataCenter().gainCardDetail("https://www.ourocg.cn/package/LTGY/AVSJP");
//		new OurocgCardDataCenter().gainCardDetail("https://www.ourocg.cn/package/LTGY-EN/zDSo9");
		new OurocgDataCenter().gainPackageDetail("https://www.ourocg.cn/package/LTGY/P1Sva");
	}
}
