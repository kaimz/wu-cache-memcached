package com.wuwii.spring.annoation;

import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author KronChan
 * @date 2019-07-02 19:53
 */
public class MemcachedConfigRegistrar implements ImportBeanDefinitionRegistrar {

  private static final String MEMCACHED_NAME = MemcachedClient.class.getName();

  @Override
  public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
      BeanDefinitionRegistry beanDefinitionRegistry) {
    if (beanDefinitionRegistry.containsBeanDefinition(MEMCACHED_NAME)) {
      return;
    }
    AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationMetadata
        .getAnnotationAttributes(EnableMemcached.class.getName()));
    String address = attributes.getString("address");

    BeanDefinitionBuilder memcachedBeanDefinition = BeanDefinitionBuilder
        .genericBeanDefinition(MemcachedClient.class);
    memcachedBeanDefinition.addPropertyValue("")
    beanDefinitionRegistry
        .registerBeanDefinition(MEMCACHED_NAME, memcachedBeanDefinition.getBeanDefinition());
  }


}
