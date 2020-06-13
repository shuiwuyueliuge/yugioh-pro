package cn.yugioh.cardsource.basic.stream;

import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.dto.transform.LimitDetail;
import cn.mayu.yugioh.common.dto.transform.LimitProto;

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
