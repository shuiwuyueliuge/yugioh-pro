package cn.mayu.yugioh.manager.config;

import cn.mayu.yugioh.common.redis.RedisFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import java.util.List;

@Configuration
public class RedisConfig {

    @Autowired
    private ReactiveRedisConnectionFactory factory;

    @Bean
    public ReactiveRedisTemplate<String, List> initRedisTemplate() {
        return RedisFactory.initStringRedisTemplate(factory, new Jackson2JsonRedisSerializer<>(List.class));
    }
}