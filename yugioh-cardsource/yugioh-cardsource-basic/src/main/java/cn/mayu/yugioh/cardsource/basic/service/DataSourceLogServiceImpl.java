package cn.mayu.yugioh.cardsource.basic.service;

import cn.mayu.yugioh.cardsource.basic.entity.DataSourceLogEntity;
import cn.mayu.yugioh.cardsource.basic.manager.CardSourceEnum;
import cn.mayu.yugioh.cardsource.basic.repository.DataSourceLogRepository;

public class DataSourceLogServiceImpl implements DataSourceLogService {

    private DataSourceLogRepository dataSourceLogRepository;

    public DataSourceLogServiceImpl(DataSourceLogRepository dataSourceLogRepository) {
        this.dataSourceLogRepository = dataSourceLogRepository;
    }

    @Override
    public void save(CardSourceEnum dataSourceType, String logType, Object data) {
        DataSourceLogEntity dataSourceLogEntity = new DataSourceLogEntity();
        dataSourceLogEntity.setDataSourceType(dataSourceType.name());
        dataSourceLogEntity.setData(data);
        dataSourceLogEntity.setLogType(logType);
        dataSourceLogRepository.save(dataSourceLogEntity).subscribe();
    }
}
