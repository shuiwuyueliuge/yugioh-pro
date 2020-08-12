package cn.mayu.yugioh.cardsource.core.ourocg;

import cn.mayu.yugioh.cardsource.basic.datacenter.LimitCenter;
import cn.mayu.yugioh.cardsource.basic.factory.AsyncNoticeManager;
import cn.mayu.yugioh.cardsource.basic.factory.LimitFactory;
import cn.mayu.yugioh.cardsource.basic.service.DataSourceLogService;
import cn.mayu.yugioh.cardsource.basic.stream.LimitPublisher;
import cn.mayu.yugioh.cardsource.basic.stream.WebSocketPublisher;
import cn.mayu.yugioh.common.dto.cardsource.LimitData;
import cn.mayu.yugioh.common.dto.transform.LimitDetail;
import cn.mayu.yugioh.common.dto.websocket.LimitAnalyseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.EventObject;
import java.util.List;

@Slf4j
@Component
public class OurocgLimitFactory extends AsyncNoticeManager implements LimitFactory {

    private LimitCenter limitCenter;

    @Autowired
    private LimitPublisher limitPublisher;

    public OurocgLimitFactory(DataSourceLogService dataSourceLogService, WebSocketPublisher webSocketPublisher) {
        super(webSocketPublisher);
        this.limitCenter = new OurocgDataCenter(dataSourceLogService);
    }

    @Override
    public void publishLimitDetail(List<String> limitUrls, String channelId, String subscribe) {
        limitUrls.stream().forEach(data -> {
            OurocgEvent ourocgEvent = new OurocgEvent(channelId, subscribe, data);
            OurocgTaskExecutor.exec(() -> gainDetailAsync(ourocgEvent));
        });
    }

    @Override
    public List<LimitData> gainLimitList() {
        String limitUrl = String.format(limitCenter.url(), "/Limit-Latest");
        return limitCenter.gainLimitList(limitUrl);
    }

    @Override
    public Object getStartResult(EventObject eventObject) {
        OurocgEvent event = (OurocgEvent) eventObject;
        return new LimitAnalyseResult(event.getData(), 65);
    }

    @Override
    public Object getEndResult(EventObject eventObject) {
        OurocgEvent event = (OurocgEvent) eventObject;
        return new LimitAnalyseResult(event.getData(), 100);
    }

    @Override
    public void doAsync(EventObject eventObject) {
        OurocgEvent event = (OurocgEvent) eventObject;
        LimitDetail limitDetail = limitCenter.gainLimitDetail(event.getData());
        limitPublisher.publish(limitDetail);
    }
}
