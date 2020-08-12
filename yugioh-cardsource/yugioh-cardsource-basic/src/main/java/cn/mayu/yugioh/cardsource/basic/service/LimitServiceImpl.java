package cn.mayu.yugioh.cardsource.basic.service;

import cn.mayu.yugioh.cardsource.basic.factory.CardSourceStrategyContext;
import cn.mayu.yugioh.cardsource.basic.factory.LimitFactory;
import cn.mayu.yugioh.common.dto.cardsource.LimitData;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class LimitServiceImpl implements LimitService {

    @Autowired
    private CardSourceStrategyContext strategyContext;

    @Override
    public void publishLimitDetail(LimitData data, Integer sourceType) {
        getLimitFactory(sourceType).publishLimitDetail(data.getUrls(), data.getChannelId(), data.getSubscribe());
    }

    @Override
    public List<LimitData> gainLimitList(Integer sourceType) {
        return getLimitFactory(sourceType).gainLimitList();
    }

    private LimitFactory getLimitFactory(Integer sourceType) {
        return strategyContext.getStrategy(sourceType).initLimitFactory();
    }
}
