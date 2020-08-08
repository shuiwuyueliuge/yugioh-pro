package cn.mayu.yugioh.cardsource.basic.factory;

import cn.mayu.yugioh.cardsource.basic.stream.WebSocketPublisher;
import cn.mayu.yugioh.common.dto.websocket.WebSocketResultEnum;
import cn.mayu.yugioh.common.dto.websocket.WebSocketSource;
import lombok.extern.slf4j.Slf4j;
import java.util.EventObject;

@Slf4j
public abstract class AsyncNoticeManager {

    private WebSocketPublisher webSocketPublisher;

    public AsyncNoticeManager(WebSocketPublisher webSocketPublisher) {
        this.webSocketPublisher = webSocketPublisher;
    }

    public void gainDetailAsync(EventObject event) {
        WebSocketSource source = (WebSocketSource) event.getSource();
        try {
            Object startResult = getStartResult(event);
            webSocketPublisher.publishWebsocket(source, WebSocketResultEnum.SUCCESS, startResult);
            doAsync(event);
            Object endResult = getEndResult(event);
            webSocketPublisher.publishWebsocket(source, WebSocketResultEnum.SUCCESS, endResult);
        } catch (Exception e) {
            log.error("ourocg gainDetailAsync error:{}", e);
            Object endResult = getEndResult(event);
            webSocketPublisher.publishWebsocket(source, WebSocketResultEnum.SYSTEM_ERROR, endResult);
        }
    }

    public abstract Object getStartResult(EventObject eventObject);

    public abstract Object getEndResult(EventObject eventObject);

    public abstract void doAsync(EventObject eventObject);
}
