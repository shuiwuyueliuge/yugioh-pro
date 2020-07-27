package cn.mayu.yugioh.websocket.service;

import cn.mayu.yugioh.websocket.model.WebSocketMsg;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ChannelSupervise {

    private static ChannelGroup GlobalGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static ConcurrentMap<String, ChannelId> ChannelMap = new ConcurrentHashMap<>();

    public static void addChannel(String requestId, Channel channel) {
        GlobalGroup.add(channel);
        ChannelMap.put(requestId, channel.id());
    }

    public static void removeChannel(Channel channel) {
        GlobalGroup.remove(channel);
        ChannelMap.remove(channel.id().asShortText());
    }

    public static Channel findChannel(String requestId) {
        Channel channel = (Channel) ChannelMap.get(requestId);
        if (channel == null) {
            return null;
        }

        return GlobalGroup.find(ChannelMap.get(requestId));
    }

    public static void send2All(TextWebSocketFrame tws) {
        GlobalGroup.writeAndFlush(tws);
    }

    public static <T> void send2One(String requestId, WebSocketMsg<T> msg) {
        String data = null;
        try {
            data = new ObjectMapper().writeValueAsString(msg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

        }

        Channel channel = findChannel(requestId);
        if (channel == null) {
            return;
        }

        channel.writeAndFlush(new TextWebSocketFrame(data));
    }
}
