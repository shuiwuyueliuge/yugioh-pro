package cn.mayu.yugioh.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Demo3Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo3Application.class, args);
    }


    @PostConstruct
    public void test() {
        new Thread(() -> new NioWebSocketServer().init()).start();
    }

    public static class NioWebSocketServer {
        private void init(){
            NioEventLoopGroup boss=new NioEventLoopGroup();
            NioEventLoopGroup work=new NioEventLoopGroup();
            try {
                ServerBootstrap bootstrap=new ServerBootstrap();
                bootstrap.group(boss,work);
                bootstrap.channel(NioServerSocketChannel.class);
                bootstrap.childHandler(new NioWebSocketChannelInitializer());
                Channel channel = bootstrap.bind(8081).sync().channel();
                channel.closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                boss.shutdownGracefully();
                work.shutdownGracefully();
            }
        }
    }

    public static class NioWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) {
            ch.pipeline().addLast("logging",new LoggingHandler("DEBUG"));//设置log监听器，并且日志级别为debug，方便观察运行流程
            ch.pipeline().addLast("http-codec",new HttpServerCodec());//设置解码器
            ch.pipeline().addLast("aggregator",new HttpObjectAggregator(65536));//聚合器，使用websocket会用到
            ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());//用于大数据的分区传输
            ch.pipeline().addLast("handler",new NioWebSocketHandler());//自定义的业务handler
        }
    }
}
