package com.wuwii.spring.boot;

import com.wuwii.property.MemcachedProperties;
import com.wuwii.spring.cache.WuMemcachedManager;
import com.wuwii.spring.core.MemcachedPostProcessor;
import com.wuwii.spring.utils.BeanRegistrationUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author kai.zhang
 * @date 2020/1/17 21:22
 */
public class ConfigMemcachedPostProcessor extends MemcachedPostProcessor implements
    BeanDefinitionRegistryPostProcessor {

  private final MemcachedPropertiesForConfig properties;

  public ConfigMemcachedPostProcessor(MemcachedPropertiesForConfig properties) {
    this.properties = properties;
  }

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
      throws BeansException {
    MemcachedProperties memcachedProperties = new MemcachedProperties();
    BeanUtils.copyProperties(properties, memcachedProperties);
    BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry, memcachedProperties);

    //BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry,
    //    MemcachedBindingPostProcessor.class.getName(), MemcachedBindingPostProcessor.class);
    if (properties.isDisableSpringCache()) {
      BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry,
          WuMemcachedManager.class.getName(), WuMemcachedManager.class);
    }
  }
}
