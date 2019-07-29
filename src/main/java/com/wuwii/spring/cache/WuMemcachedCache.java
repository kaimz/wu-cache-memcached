package com.wuwii.spring.cache;

import java.util.concurrent.Callable;
import org.springframework.cache.Cache;

/**
 * @author KronChan
 * @date 2019-07-29 07:43
 */
public class WuMemcachedCache implements Cache {

  @Override
  public String getName() {
    return null;
  }

  @Override
  public Object getNativeCache() {
    return null;
  }

  @Override
  public ValueWrapper get(Object o) {
    return null;
  }

  @Override
  public <T> T get(Object o, Class<T> aClass) {
    return null;
  }

  @Override
  public <T> T get(Object o, Callable<T> callable) {
    return null;
  }

  @Override
  public void put(Object o, Object o1) {

  }

  @Override
  public ValueWrapper putIfAbsent(Object o, Object o1) {
    return null;
  }

  @Override
  public void evict(Object o) {

  }

  @Override
  public void clear() {

  }
}
