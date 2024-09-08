package com.hj.timebean.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.List;

// 사용 안함
//Slf4j
//@Configurable
//@EnableCaching
public class CacheConfig {
//    @Bean
//    public CacheManager cacheManager() {
//        log.debug("[+] CacheConfig Start !!! ");
//        System.out.println("cache start!!!!!!!!");
//        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
//        cacheManager.setAllowNullValues(false);
//        cacheManager.setCacheNames(List.of("rankings"));
//        return cacheManager;
//    }
}
