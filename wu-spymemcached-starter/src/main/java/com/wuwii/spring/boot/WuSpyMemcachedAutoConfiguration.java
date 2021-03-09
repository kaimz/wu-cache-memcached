package com.wuwii.spring.boot;

import com.wuwii.spring.handle.annotation.MemcachedConfigRegistrar;
import com.wuwii.spring.property.MemcachedProperties;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

  private final MemcachedPropertiesForConfig memcachedPropertiesForConfig;

  public WuSpyMemcachedAutoConfiguration(
      final MemcachedPropertiesForConfig memcachedPropertiesForConfig) {
    this.memcachedPropertiesForConfig = memcachedPropertiesForConfig;
  }

  @Bean
  public ConfigMemcachedProcessor configMemcachedProcessor() {
    return new ConfigMemcachedProcessor(memcachedPropertiesForConfig);
  }


}


