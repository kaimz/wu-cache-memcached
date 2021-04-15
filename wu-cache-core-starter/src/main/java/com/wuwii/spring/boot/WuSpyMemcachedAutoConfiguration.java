package com.wuwii.spring.boot;

import com.wuwii.property.MemcacheBeanConstants;
import com.wuwii.property.MemcachedProperties;
import com.wuwii.property.WuMemcachedFactory;
import com.wuwii.spring.config.MemcachedClient;
import com.wuwii.spring.core.MemcachedBindingPostProcessor;
import com.wuwii.spring.handle.annotation.MemcachedConfigRegistrar;
import org.springframework.beans.BeanUtils;
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

  @Bean
  public static MemcachedBindingPostProcessor memcachedBindingPostProcessor() {
    return new MemcachedBindingPostProcessor();
  }

  @Bean
  public MemcachedProperties memcachedProperties(
      MemcachedPropertiesForConfig memcachedPropertiesForConfig) throws Exception {
    MemcachedProperties memcachedProperties = new MemcachedProperties();
    BeanUtils.copyProperties(memcachedPropertiesForConfig, memcachedProperties);
    return memcachedProperties;
  }

  @Bean(MemcacheBeanConstants.WU_FACTORY_BEAN_NAME)
  public WuMemcachedFactory wuMemcachedFactory(MemcachedProperties properties) {
    MemcachedClient memcachedClient = new MemcachedClient();
    memcachedClient.setMemcachedProperties(properties);
    return memcachedClient;
  }

}


