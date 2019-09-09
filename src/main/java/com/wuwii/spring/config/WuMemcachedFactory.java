package com.wuwii.spring.config;

import com.wuwii.spring.property.MemcachedKey;
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
  Object get(MemcachedKey key);

  /**
   * get from cache
   *
   * @param key key
   * @param type value type
   * @param <T> value class
   * @return if key not exist return null
   */
  <T> T get(MemcachedKey key, Class<T> type);

  /**
   * get from cache
   *
   * @param key key
   * @param valueLoader if key not exist,call this value loader
   * @param <T> value class
   * @return value
   */
  <T> T get(MemcachedKey key, Callable<T> valueLoader);

  /**
   * put key value
   */
  void put(MemcachedKey key, Object value);

  /**
   * Put while the cache does not exist this key
   *
   * @return key's value
   */
  Object putIfAbsent(MemcachedKey key, Object value);

  /**
   * delete key from cache
   */
  void evict(MemcachedKey key);

  /**
   * clear cache
   */
  void clear();
}
