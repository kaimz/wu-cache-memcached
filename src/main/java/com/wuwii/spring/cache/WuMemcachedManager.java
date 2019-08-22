package com.wuwii.spring.cache;

import com.wuwii.spring.annotation.WuMemcachedConfig;
import com.wuwii.spring.config.WuMemcachedFactory;
import java.util.Collection;
import java.util.Collections;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author KronChan
 * @date 2019-07-29 07:09
 */
@EnableCaching // 用于开启注释驱动的 spring cache
public class WuMemcachedManager implements CacheManager {

  @WuMemcachedConfig
  private WuMemcachedFactory wuMemcached;


  @Override
  public Cache getCache(String name) {
    if ("wuwii".equals(name)) {
      return wuMemcached;
    }
    return null;
  }

  @Override
  public Collection<String> getCacheNames() {
    return Collections.singleton("wuwii");
  }
}
