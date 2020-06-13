package cn.yugioh.cardsource.basic.stream;

import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.transform.CardProto;
import cn.mayu.yugioh.common.dto.transform.PackageDetail;
import cn.mayu.yugioh.common.dto.transform.PackageProto;

public class PackageDetailConverterFactory implements DomainConverterFactory<PackageDetail, PackageProto.PackageDetail> {

    private DomainConverterFactory<CardDetail, CardProto.CardDetail> cardDetailConverterFactory;

    public PackageDetailConverterFactory() {
        cardDetailConverterFactory = new CardDetailConverterFactory();
    }

    @Override
    public PackageProto.PackageDetail convert(PackageDetail source) {
        PackageProto.PackageDetail.Builder build = PackageProto.PackageDetail.newBuilder();
        build.addAllCards(cardDetailConverterFactory.convertList(source.getCards()));
        build.setPackageName(source.getPackageName());
        build.setOfferingDate(source.getOfferingDate());
        build.setCardCount(source.getCardCount());
        build.setAbbreviate(source.getAbbreviate());
        build.setPackageImg(source.getPackageImg());
        build.setCoverImg(source.getCoverImg());
        return build.build();
    }
}
