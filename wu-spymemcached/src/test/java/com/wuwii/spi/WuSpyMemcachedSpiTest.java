package com.wuwii.spi;

import com.wuwii.property.support.WuSpyMemcached;

/**
 * @author KronChan
 * @date 2019-08-22 20:49
 */
public class WuSpyMemcachedSpiTest extends WuSpyMemcached {

  @Override
  public int getOrder() {
    return 0;
  }
}
