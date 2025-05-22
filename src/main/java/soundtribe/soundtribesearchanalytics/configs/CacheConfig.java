package soundtribe.soundtribesearchanalytics.configs;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        // Cache de 1 hora
        Cache dashboardsCache = new CaffeineCache("dashboardsCache",
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.HOURS)
                        .maximumSize(2000)
                        .build());


        // Agregá todos los caches al manager
        cacheManager.setCaches(Arrays.asList(
                dashboardsCache
        ));

        return cacheManager;
    }
}
