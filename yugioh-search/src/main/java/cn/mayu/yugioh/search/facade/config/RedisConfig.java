package cn.mayu.yugioh.search.facade.config;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.redis.IntRedisSerializer;
import cn.mayu.yugioh.common.redis.RedisConfigContext;
import cn.mayu.yugioh.common.redis.YugiohRedisFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

    @Autowired
    private ReactiveRedisConnectionFactory factory;

    @Bean
    public ReactiveRedisTemplate<Integer, CardDetail> IndexEntityRedis() {
        RedisConfigContext<Integer, CardDetail> context = new RedisConfigContext<Integer, CardDetail>(new IntRedisSerializer(),
                new Jackson2JsonRedisSerializer<CardDetail>(CardDetail.class),
                factory);
        return YugiohRedisFactory.reactiveRedisTemplate(context);
    }
}