package cn.mayu.yugioh.cardsource.basic.service;

import cn.mayu.yugioh.common.dto.cardsource.LimitData;
import java.util.List;

public interface LimitService {

    void publishLimitDetail(LimitData limitData, Integer sourceType);

    List<LimitData> gainLimitList(Integer sourceType);
}
