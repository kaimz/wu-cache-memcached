package com.wuwii.spring.boot;

import com.wuwii.spring.annotation.MemcachedConfigRegistrar;
import com.wuwii.spring.property.MemcachedProperties;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author kai.zhang
 * @date 2020/1/17 20:14
 */
@Configuration
@EnableConfigurationProperties(MemcachedPropertiesForConfig.class)
@ConditionalOnMissingBean({MemcachedConfigRegistrar.class, MemcachedProperties.class})
@AutoConfigureOrder
@ConditionalOnProperty(prefix = "wu.memcached", name = "enable",
    havingValue = "true", matchIfMissing = true)
public class WuSpyMemcachedAutoConfiguration {

  @Bean
  @ConditionalOnBean(MemcachedPropertiesForConfig.class)
  @Order
  public ConfigMemcachedProcessor configMemcachedProcessor(
      MemcachedPropertiesForConfig memcachedPropertiesForConfig) {
    return new ConfigMemcachedProcessor(memcachedPropertiesForConfig);
  }

  @Configuration
  protected static class MemcachedConnectionConfiguration {


  }


}


