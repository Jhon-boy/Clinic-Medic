package com.appmedic.medic_app.config.cache;

import com.appmedic.medic_app.config.AplicationProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@EnableCaching
public class CacheConfigDB {

    public static  final String CACHE_NAME = "track";
    private final String URL_ADDRESS;
    public  CacheConfigDB(AplicationProperties aplicationProperties){
        this.URL_ADDRESS = aplicationProperties.getRedis().getUrl();
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisClient(){
        var config = new Config();
        config.useSingleServer()
                .setAddress(URL_ADDRESS);

        return Redisson.create(config);
    }

    @Bean
    @Autowired
    public CacheManager cacheManager( RedissonClient redisClient){
        var config = Collections.singletonMap(CACHE_NAME, new CacheConfig());
        return new  RedissonSpringCacheManager(redisClient, config);
    }

}
