package cn.mayu.yugioh.search.config;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.redis.RedisFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;

@Configuration
public class RedisConfig {

    @Autowired
    private ReactiveRedisConnectionFactory factory;

    @Bean
    public ReactiveRedisTemplate<Integer, CardDetail> IndexEntityRedis() {
        return RedisFactory.initIntegerRedisTemplate(factory, CardDetail.class);
    }
}