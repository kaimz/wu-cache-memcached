package com.wuwii.spring;

import lombok.experimental.Delegate;
import net.spy.memcached.MemcachedClient;

/**
 * @author KronChan
 * @date 2019-07-04 19:57
 */
public class WuMemcached {

  @Delegate
  private MemcachedClient memcachedClient;

  public WuMemcached() {

  }
}
