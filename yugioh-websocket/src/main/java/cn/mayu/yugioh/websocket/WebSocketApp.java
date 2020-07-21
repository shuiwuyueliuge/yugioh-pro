package cn.mayu.yugioh.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebSocketApp {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WebSocketApp.class);
        app.run(args);
    }
}
