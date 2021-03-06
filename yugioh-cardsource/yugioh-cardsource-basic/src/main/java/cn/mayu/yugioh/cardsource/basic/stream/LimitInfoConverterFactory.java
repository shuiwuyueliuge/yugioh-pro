package cn.mayu.yugioh.cardsource.basic.stream;

import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.dto.transform.LimitDetail;
import cn.mayu.yugioh.common.dto.transform.LimitProto;

public class LimitInfoConverterFactory implements DomainConverterFactory<LimitDetail, LimitProto.LimitDetail> {

    @Override
    public LimitProto.LimitDetail convert(LimitDetail source) {
        LimitProto.LimitDetail.Builder build = LimitProto.LimitDetail.newBuilder();
        build.setName(source.getName());
        build.addAllForbidden(source.getForbidden());
        build.setPublishTime(source.getPublishTime());
        build.addAllLimited(source.getLimited());
        build.addAllSemiLimited(source.getSemiLimited());
        build.setRegion(source.getRegion());
        return build.build();
    }
}
