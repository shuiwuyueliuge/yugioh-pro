package cn.mayu.yugioh.cardsource.ourocg;

import cn.mayu.yugioh.cardsource.ourocg.model.LimitInfo;
import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.dto.cardsource.LimitProto.LimitDetail;

public class LimitInfoConverterFactory implements DomainConverterFactory<LimitInfo, LimitDetail> {

    @Override
    public LimitDetail convert(LimitInfo source) {
        LimitDetail.Builder build = LimitDetail.newBuilder();
        build.setName(source.getName());
        build.addAllForbidden(source.getForbidden());
        build.addAllLimited(source.getLimited());
        build.addAllSemiLimited(source.getSemiLimited());
        return build.build();
    }
}
