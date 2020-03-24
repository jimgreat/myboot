package com.yp.config;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

//@Configuration
public class EhCacheConfig {
    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
        return ehCacheCacheManager;
    }


    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        //这里暂时借用shiro的ehcache配置文件
        Resource r=new ClassPathResource("ehcache.xml");
        cacheManagerFactoryBean.setConfigLocation(r);
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }
}
