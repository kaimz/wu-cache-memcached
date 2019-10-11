package com.wuwii.spring.spi;

import com.wuwii.spring.config.WuMemcachedFactory;
import com.wuwii.spring.property.MemcachedProperties;
import org.springframework.core.PriorityOrdered;

import java.io.IOException;

/**
 * @author KronChan
 * @date 2019-08-02 21:26
 */
public interface WuMemcachedStartHelper extends WuMemcachedFactory, PriorityOrdered {

  /**
   * create instance and connect cache server
   *
   * @param properties connect properties
   * @return WuMemcachedFactory
   * @throws IOException while connect failed
   */
  WuMemcachedFactory of(MemcachedProperties properties) throws IOException;
}
