package springboot.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springboot.cache.RedisCacheManager;

@Configuration
@EnableCaching
public class SimpleRedisCacheConfiguration {
    @Bean
    @Primary
    public CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager("redis://127.0.0.1:6379/");
        return cacheManager;
    }
}
