package com.example.baseproject.utils.configs
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import java.time.Duration

@Configuration
@EnableCaching
class RedisConfig {

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        // 配置 redisTemplate
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory)
        val stringSerializer: RedisSerializer<String> = StringRedisSerializer()
        redisTemplate.keySerializer = stringSerializer // key序列化
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer() // value序列化
        redisTemplate.hashKeySerializer = stringSerializer // Hash key序列化
        redisTemplate.hashValueSerializer = GenericJackson2JsonRedisSerializer() // Hash value序列化
        redisTemplate.afterPropertiesSet()
        return redisTemplate
    }

    @Bean
    @ConditionalOnProperty(name = ["spring.cache.type"], havingValue = "redis")
    fun redisCacheManager(connectionFactory: RedisConnectionFactory): CacheManager {
        val cacheConfigurations = HashMap<String, RedisCacheConfiguration>()
        cacheConfigurations["base-project"] = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMillis(300000))

        return RedisCacheManager.builder(connectionFactory)
            .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMillis(86400000)))
            .withInitialCacheConfigurations(cacheConfigurations)
            .build()
    }
}
