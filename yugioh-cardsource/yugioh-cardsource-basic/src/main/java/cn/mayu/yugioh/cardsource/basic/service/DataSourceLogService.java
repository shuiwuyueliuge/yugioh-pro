package cn.mayu.yugioh.cardsource.basic.service;

import cn.mayu.yugioh.cardsource.basic.factory.CardSourceEnum;

public interface DataSourceLogService {

    void save(CardSourceEnum dataSourceType, String logType, Object data);
}
