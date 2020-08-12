package cn.mayu.yugioh.cardsource.basic.service;

import cn.mayu.yugioh.common.dto.cardsource.SourceType;
import java.util.List;
import java.util.stream.Collectors;
import static cn.mayu.yugioh.cardsource.basic.factory.CardSourceEnum.sources;

public class CardSourceServiceImpl implements CardSourceService {

    @Override
    public List<SourceType> getSourceType() {
        return sources().stream().map(data -> new SourceType(data.toString(), data.getType())).collect(Collectors.toList());
    }
}
