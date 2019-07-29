package com.wuwii.spring.config;

import java.io.IOException;
import org.springframework.context.annotation.Configuration;

/**
 * @author KronChan
 * @date 2019-06-27 23:23
 */
@Configuration
public class MemcachedConfig {

  /**
   * Inject memcached client while memcached properties exist
   *
   * @param memcachedProperties memcached properties
   * @return MemcachedClient bean
   * @throws IOException connect memcached client fail
   */
//  @Bean
////  @Conditional(MemcachedCondition.class)
////  public WuMemcached wuMemcached(MemcachedProperties memcachedProperties)
////      throws IOException {
////    return WuMemcached.of(memcachedProperties);
////  }
}
