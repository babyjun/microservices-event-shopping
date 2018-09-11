package com.qjm.common.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @author qianjm
 * @date 2018/9/6
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

  @Override
  @Bean
  public KeyGenerator keyGenerator(){
    return (o,method,objects)->{
      StringBuilder sb = new StringBuilder();
      sb.append(o.getClass().getName());
      sb.append(method.getName());
      for(Object object:objects){
        sb.append(object.toString());
      }
      return sb.toString();
    };
  }

  @Bean
  public CacheManager cacheManager(RedisTemplate redisTemplate){
    RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
    // 默认缓存时间,单位秒，缓存2小时
    redisCacheManager.setDefaultExpiration(60 * 60 * 2);
    // 自定义过期时间, 使用Cache(value="two_hour")
    Map<String, Long> map = new HashMap<String, Long>(2);
    map.put("currentUser", 43200L);
    map.put("config.findAll", 604800L);
    map.put("role.findAll", 604800L);
    return redisCacheManager;
  }

  /**
   * .注入redis连接模板
   */
  @Bean
  public RedisTemplate<String, String> redisTemplate(
      RedisConnectionFactory redisConnectionFactory) {
    StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
    setSerializer(stringRedisTemplate);
    stringRedisTemplate.afterPropertiesSet();
    return stringRedisTemplate;
  }

  private void setSerializer(StringRedisTemplate template) {
    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(
        Object.class);
    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(om);
    template.setValueSerializer(jackson2JsonRedisSerializer);
  }
}
