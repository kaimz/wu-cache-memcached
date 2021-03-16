package com.wuwii.spi;

import com.wuwii.property.MemcachedProperties;
import com.wuwii.property.WuMemcachedFactory;
import java.io.IOException;
import org.springframework.core.PriorityOrdered;

/**
 * @author KronChan
 * @date 2019-08-02 21:26
 */
public interface WuMemcachedStartHelper extends PriorityOrdered {

  /**
   * create instance and connect cache server
   *
   * @param properties connect properties
   * @return WuMemcachedFactory
   * @throws IOException while connect failed
   */
  WuMemcachedFactory of(MemcachedProperties properties) throws IOException;
}
