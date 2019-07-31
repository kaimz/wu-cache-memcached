package com.wuwii.spring.cache;

import com.wuwii.spring.annotation.WuMemcachedConfig;
import com.wuwii.spring.property.WuMemcached;
import java.util.Collection;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.AbstractCacheManager;

/**
 * @author KronChan
 * @date 2019-07-29 07:09
 */
@EnableCaching // 用于开启注释驱动的 spring cache
public class WuMemcachedManager extends AbstractCacheManager {

  @WuMemcachedConfig
  private WuMemcached wuMemcached;

  @Override
  protected Collection<? extends Cache> loadCaches() {
    return null;
  }
}
