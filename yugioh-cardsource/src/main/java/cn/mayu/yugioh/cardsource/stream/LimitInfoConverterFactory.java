package cn.mayu.yugioh.cardsource.stream;

import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.dto.cardsource.LimitDetail;
import cn.mayu.yugioh.common.dto.cardsource.LimitProto;

public class LimitInfoConverterFactory implements DomainConverterFactory<LimitDetail, LimitProto.LimitDetail> {

    @Override
    public LimitProto.LimitDetail convert(LimitDetail source) {
        LimitProto.LimitDetail.Builder build = LimitProto.LimitDetail.newBuilder();
        build.setName(source.getName());
        build.addAllForbidden(source.getForbidden());
        build.addAllLimited(source.getLimited());
        build.addAllSemiLimited(source.getSemiLimited());
        return build.build();
    }
}
