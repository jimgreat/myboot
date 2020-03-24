package com.jm.config;

//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import redis.clients.jedis.JedisPoolConfig;

//@Configuration
public class RedisConfig {

//    @Bean
//    public JedisPoolConfig getJedisPoolConfig() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(4);
//        jedisPoolConfig.setMaxTotal(8);
//        jedisPoolConfig.setMinIdle(1);
//        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
//        jedisPoolConfig.setMaxWaitMillis(15000);
//        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
//        jedisPoolConfig.setMinEvictableIdleTimeMillis(300000);
//        jedisPoolConfig.setNumTestsPerEvictionRun(3);
//        //一个连接在池中最小生存的时间
//        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(60000);
//        jedisPoolConfig.setBlockWhenExhausted(true);
//        return jedisPoolConfig;
//    }
//
//
//    @Bean(name = "jedisConnectionFactory")
//    public JedisConnectionFactory getJedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
//        jedisConnectionFactory.setHostName("10.38.163.216");
//        jedisConnectionFactory.setPort(6379);
////        jedisConnectionFactory.setPassword(redisPassword);
////        jedisConnectionFactory.setTimeout(redisProperties.getTimeout());
//        return jedisConnectionFactory;
//    }
//
//    @Bean
//    public RedisTemplate getRedisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory){
//        RedisTemplate redisTemplate = new RedisTemplate();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory);
//        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        return redisTemplate;
//    }
}
