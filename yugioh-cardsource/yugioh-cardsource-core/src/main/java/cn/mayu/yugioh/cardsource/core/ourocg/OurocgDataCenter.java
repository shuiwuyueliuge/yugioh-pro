package cn.mayu.yugioh.cardsource.core.ourocg;

import static cn.mayu.yugioh.cardsource.basic.factory.CardSourceEnum.OUROCG;
import cn.mayu.yugioh.cardsource.basic.service.DataSourceLogService;
import cn.mayu.yugioh.common.dto.cardsource.LimitData;
import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.transform.LimitDetail;
import cn.mayu.yugioh.common.dto.transform.PackageDetail;
import cn.mayu.yugioh.cardsource.basic.datacenter.LimitCenter;
import cn.mayu.yugioh.cardsource.basic.datacenter.PackageCenter;
import cn.mayu.yugioh.cardsource.core.ourocg.html.*;
import cn.mayu.yugioh.cardsource.core.ourocg.model.*;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static cn.mayu.yugioh.common.core.util.JsonUtil.readValueSnakeCase;

public class OurocgDataCenter implements PackageCenter, LimitCenter {

    private String description;

    private DataSourceLogService dataSourceLogService;

    public OurocgDataCenter(DataSourceLogService dataSourceLogService) {
        this.dataSourceLogService = dataSourceLogService;
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
        String data = OurocgHtmlHandlers.cardDataTranslater.handle(resources);
        OurocgMetaData metaData = readToOurocgMetaData(data);
        PackageDetail packageDetail = new PackageDetail();
        dataSourceLogService.save(OUROCG, "package", metaData);
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
            Include cd = OurocgHtmlHandlers.includeTranslater.handle(ourocgCard.getHref());
            dataSourceLogService.save(OUROCG, "include", cd);
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
                continue;
            }

            // 英文版卡包
            packageDetail.getEnPackages().add(info.getHref());
        }
    }

    @Override
    public List<PackageData> gainPackageList(String resources) {
        return OurocgHtmlHandlers.packageListTranslater.handle(resources);
    }

    @Override
    public LimitDetail gainLimitDetail(String resources) {
        LimitInfo limitInfo = OurocgHtmlHandlers.limitDataTranslater.handle(resources);
        dataSourceLogService.save(OUROCG, "limit", limitInfo);
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
    public List<LimitData> gainLimitList(String resources) {
        return OurocgHtmlHandlers.limitListTranslater.handle(resources);
    }
}
