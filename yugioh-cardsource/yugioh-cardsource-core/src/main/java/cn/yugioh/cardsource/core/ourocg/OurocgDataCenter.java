package cn.yugioh.cardsource.core.ourocg;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.transform.LimitDetail;
import cn.mayu.yugioh.common.dto.transform.PackageDetail;
import cn.yugioh.cardsource.basic.datacenter.LimitCenter;
import cn.yugioh.cardsource.basic.datacenter.PackageCenter;
import cn.yugioh.cardsource.basic.html.*;
import cn.yugioh.cardsource.core.ourocg.html.*;
import cn.yugioh.cardsource.core.ourocg.model.*;
import cn.yugioh.cardsource.core.ourocg.repository.OurocgIncludeRepository;
import cn.yugioh.cardsource.core.ourocg.repository.OurocgLimitRepository;
import cn.yugioh.cardsource.core.ourocg.repository.OurocgPackageRepository;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static cn.mayu.yugioh.common.core.util.JsonUtil.readValueSnakeCase;
import static cn.yugioh.cardsource.core.ourocg.DataTypeEnum.PACKAGE;
import static cn.yugioh.cardsource.core.ourocg.OurocgQueueGuardian.addOne;

public class OurocgDataCenter implements PackageCenter, LimitCenter {

    private String description;

    private HtmlHandler<String> cardDataTranslater;

    private HtmlHandler<Include> includeTranslater;

    private HtmlHandler<List<PackageData>> packageListTranslater;

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
        String[] packageHtml = metaData.getMeta().getMetaKw().split(",");
        packageDetail.setCardCount(metaData.getMeta().getCount());
        packageDetail.setPackageName(packageHtml[1].trim());
        packageDetail.setAbbreviate(packageHtml[0].trim());
        packageDetail.setCards(getCardDetailList(packageDetail, metaData.getCards()));
    }

    private List<CardDetail> getCardDetailList(PackageDetail packageDetail, List<OurocgCard> cards) {
        return cards.stream().map(ourocgCard -> {
            CardDetail cardDetail = new CardDetail();
            BeanUtils.copyProperties(ourocgCard, cardDetail);
            cardDetail.setTypeSt(Stream.of(ourocgCard.getTypeSt().split("\\|")).collect(Collectors.toList()));
            if (ourocgCard.getLinkArrow().indexOf(",") != -1) {
                cardDetail.setLinkArrow(Stream.of(ourocgCard.getLinkArrow().split(","))
                        .map(Integer::valueOf).map(OurocgLinkArrowEnum::getArrow).collect(Collectors.toList()));
            } else {
                cardDetail.setLinkArrow(new ArrayList<>());
            }

            // 收录详情/调整 解析
            Include cd = includeTranslater.handle(ourocgCard.getHref());
            includeRepository.save(cd).subscribe();
            cardDetail.setAdjust(cd.getAdjust());
            saveIncludeInfo(cd, cardDetail, packageDetail);
            return cardDetail;
        }).collect(Collectors.toList());
    }

    private void saveIncludeInfo(Include cd, CardDetail card, PackageDetail packageDetail) {
        for (IncludeInfo info : cd.getIncludeInfos()) {
            if (packageDetail.getPackageName().indexOf(info.getPackName()) == -1) {
                continue;
            }

            if (packageDetail.getPackageName().equals(info.getPackName())) {// 日文版卡包
                packageDetail.setOfferingDate(info.getSellTime());
                card.setSerial(info.getNumber());
                card.getRare().add(info.getRare());
            } else {// 英文版卡包重新放入队列
                addOne(info.getHref(), 0, PACKAGE);
            }
        }
    }

    @Override
    public List<PackageData> gainPackageList(String resources) {
        return packageListTranslater.handle(resources);
    }

    @Override
    public LimitDetail gainLimitDetail(String resources) {
        LimitInfo limitInfo = limitDataTranslater.handle(resources);
        limitRepository.save(limitInfo).subscribe();
        LimitDetail limitDetail = new LimitDetail();
        limitDetail.setName(limitInfo.getName());
        limitDetail.setForbidden(limitInfo.getForbidden());
        limitDetail.setLimited(limitInfo.getLimited());
        limitDetail.setSemiLimited(limitInfo.getSemiLimited());
        limitDetail.setPublishTime(limitInfo.getPublishTime());
        limitDetail.setRegion(limitInfo.getRegion());
        return limitDetail;
    }

    @Override
    public List<String> gainLimitList(String resources) {
        return limitListTranslater.handle(resources);
    }
}
