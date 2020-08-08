package cn.mayu.yugioh.cardsource.core.ourocg;

import cn.mayu.yugioh.common.dto.websocket.WebSocketSource;
import lombok.*;
import java.util.EventObject;
import java.util.Objects;

@Getter
@ToString
public class OurocgEvent extends EventObject {

    private String data;

    public OurocgEvent(String channelId, String subscribe, String data) {
        super(new WebSocketSource(channelId, subscribe));
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OurocgEvent that = (OurocgEvent) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
