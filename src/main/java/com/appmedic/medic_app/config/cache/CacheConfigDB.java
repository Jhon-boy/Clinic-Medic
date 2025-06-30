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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableCaching
public class CacheConfigDB {

    public static  final String CACHE_NAME = "track";
    public static final String CACHE_PAYS = "payments";
    public static final String CACHE_EMPLOYES = "employes";

    private final String urlAddress;
    public  CacheConfigDB(AplicationProperties aplicationProperties){
        this.urlAddress = aplicationProperties.getRedis().getUrl();
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisClient(){
        var config = new Config();
        config.useSingleServer()
                .setAddress(urlAddress);

        return Redisson.create(config);
    }

    @Bean
    @Autowired
    public CacheManager cacheManager( RedissonClient redisClient){
        List<String> caches = List.of(CACHE_PAYS, CACHE_EMPLOYES, CACHE_NAME);
        Map<String, CacheConfig> config = caches.stream().collect(Collectors.toMap(name -> name, name -> new CacheConfig()));
        return new  RedissonSpringCacheManager(redisClient, config);
    }
}
