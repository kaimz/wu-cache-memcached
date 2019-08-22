package com.wuwii.spring;

import static com.wuwii.spring.SpringCacheApplicationTest.VALUE;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author KronChan
 * @date 2019-08-21 21:51
 */
@Service
@CacheConfig(cacheNames = "wuwii")
public class SpringCacheTestImpl implements SpringCacheTest {

  @Override
  @Cacheable(key = "#p0")
  public String get(String key) {
    System.out.println("没走缓存");
    return VALUE;
  }
}
