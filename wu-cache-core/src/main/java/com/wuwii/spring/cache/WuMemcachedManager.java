package com.wuwii.spring.cache;

import com.wuwii.spring.annotation.WuMemcachedConfig;
import com.wuwii.spring.config.WuMemcachedFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author KronChan
 * @date 2019-07-29 07:09
 */
@EnableCaching // 用于开启注释驱动的 spring cache
public class WuMemcachedManager implements CacheManager {

  private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>(16);
  private boolean dynamic = true;

  @WuMemcachedConfig
  private WuMemcachedFactory wuMemcached;

  public WuMemcachedManager() {
  }

  public WuMemcachedManager(String... cacheNames) {
    this.setCachedNames(Arrays.asList(cacheNames));
  }

  public void setCachedNames(Collection<String> cachedNames) {
    if (cachedNames != null) {
      for (String cachedName : cachedNames) {
        cacheMap.put(cachedName, new WuMemcachedCache(cachedName, wuMemcached));
      }
      this.dynamic = false;
    } else {
      this.dynamic = true;
    }
  }

  @Override
  public Cache getCache(String name) {
    Cache cache = cacheMap.get(name);
    if (cache == null && this.dynamic) {
      synchronized (this.cacheMap) {
        cache = this.cacheMap.get(name);
        if (cache == null) {
          cache = new WuMemcachedCache(name, wuMemcached);
        }
      }
    }
    return cache;
  }

  @Override
  public Collection<String> getCacheNames() {
    return Collections.unmodifiableSet(cacheMap.keySet());
  }
}
