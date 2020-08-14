package cn.mayu.yugioh.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCaching
@EnableFeignClients(basePackages = {"cn.mayu.yugioh.common.facade.cardsource", "cn.mayu.yugioh.common.facade.search"})
public class ManagerApp {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ManagerApp.class);
        app.run(args);
    }
}
