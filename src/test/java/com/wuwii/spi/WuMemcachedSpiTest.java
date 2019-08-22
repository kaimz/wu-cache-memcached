package com.wuwii.spi;

import com.wuwii.spring.property.WuMemcached;

/**
 * @author KronChan
 * @date 2019-08-22 20:49
 */
public class WuMemcachedSpiTest extends WuMemcached {

  @Override
  public int getOrder() {
    return 0;
  }
}
