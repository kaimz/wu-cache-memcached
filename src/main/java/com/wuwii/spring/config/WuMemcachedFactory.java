package com.wuwii.spring.config;

import java.util.concurrent.Callable;

/**
 * @author KronChan
 * @date 2019-08-02 20:26
 */
public interface WuMemcachedFactory {

  /**
   * get from cache
   *
   * @param key key
   * @return if key not exist return null
   */
  Object get(Object key);

  /**
   * get from cache
   *
   * @param key key
   * @param type value type
   * @param <T> value class
   * @return if key not exist return null
   */
  <T> T get(Object key, Class<T> type);

  /**
   * get from cache
   *
   * @param key key
   * @param valueLoader if key not exist,call this value loader
   * @param <T> value class
   * @return value
   */
  <T> T get(Object key, Callable<T> valueLoader);

  /**
   * put key value
   */
  void put(Object key, Object value);

  /**
   * Put while the cache does not exist this key
   *
   * @return key's value
   */
  Object putIfAbsent(Object key, Object value);

  /**
   * delete key from cache
   */
  void evict(Object key);

  /**
   * clear cache
   */
  void clear();
}
