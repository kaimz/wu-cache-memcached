package com.wuwii.spring.cache;

import com.wuwii.spring.config.WuMemcachedFactory;
import com.wuwii.spring.property.WithPrefixMemcachedKey;
import java.util.concurrent.Callable;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.Assert;

/**
 * @author KronChan
 * @date 2019/9/8 3:26 下午
 */
public class WuMemcachedCache extends AbstractValueAdaptingCache {

  /**
   * cache name
   */
  private String name;

  private final WuMemcachedFactory wuMemcachedFactory;

  public WuMemcachedCache(String name, WuMemcachedFactory wuMemcachedFactory) {
    this(name, wuMemcachedFactory, true);
  }

  public WuMemcachedCache(String name, WuMemcachedFactory wuMemcachedFactory,
      boolean allowNullValues) {
    super(allowNullValues);
    Assert.notNull(name, "Name must not be null");
    Assert.notNull(wuMemcachedFactory, "Cache must not be null");
    this.name = name;
    this.wuMemcachedFactory = wuMemcachedFactory;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Object getNativeCache() {
    return this.wuMemcachedFactory;
  }

  @Override
  protected Object lookup(Object key) {
    return wuMemcachedFactory.get(resolveKey(key.toString()));
  }

  @Override
  public <T> T get(Object key, Callable<T> valueLoader) {
    return wuMemcachedFactory.get(resolveKey(key.toString()), valueLoader);
  }

  @Override
  public void put(Object key, Object value) {
    wuMemcachedFactory.put(resolveKey(key.toString()), value);
  }

  @Override
  public ValueWrapper putIfAbsent(Object key, Object value) {
    Object o = wuMemcachedFactory.putIfAbsent(resolveKey(key.toString()), value);
    return o == null ? null : new SimpleValueWrapper(o);
  }

  @Override
  public void evict(Object key) {
    wuMemcachedFactory.evict(resolveKey(key.toString()));
  }

  @Override
  public void clear() {
    wuMemcachedFactory.clear();
  }

  private WithPrefixMemcachedKey resolveKey(String key) {
    return new WithPrefixMemcachedKey(key, name);
  }
}
