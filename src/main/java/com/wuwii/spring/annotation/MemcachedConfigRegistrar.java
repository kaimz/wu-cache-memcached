package com.wuwii.spring.annotation;

import com.wuwii.spring.config.MemcachedProperties;
import com.wuwii.spring.utils.BeanRegistrationUtil;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author KronChan
 * @date 2019-07-02 19:53
 */
public class MemcachedConfigRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
      BeanDefinitionRegistry beanDefinitionRegistry) {
    AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationMetadata
        .getAnnotationAttributes(EnableMemcached.class.getName()));
    String host = attributes.getString("host");
    Integer port = (Integer) attributes.get("port");
    String username = attributes.getString("username");
    String password = attributes.getString("password");
    Map<String, Object> propertySourcesPlaceholderPropertyValues = new HashMap<>();
    propertySourcesPlaceholderPropertyValues.put("host", host);
    propertySourcesPlaceholderPropertyValues.put("port", port);
    propertySourcesPlaceholderPropertyValues.put("username", username);
    propertySourcesPlaceholderPropertyValues.put("password", password);
    /*BeanRegistrationUtil.registerBeanDefinitionIfNotExists(beanDefinitionRegistry, PropertySourcesPlaceholderConfigurer.class.getName(),
        PropertySourcesPlaceholderConfigurer.class, propertySourcesPlaceholderPropertyValues);*/
    BeanRegistrationUtil
        .registerBeanDefinitionIfNotExists(beanDefinitionRegistry,
            MemcachedProperties.class.getName(),
            MemcachedProperties.class, propertySourcesPlaceholderPropertyValues);
/*    BeanDefinitionBuilder memcachedBeanDefinition = BeanDefinitionBuilder
        .genericBeanDefinition(MemcachedClient.class);
    memcachedBeanDefinition.addPropertyValue("", "");
    beanDefinitionRegistry
        .registerBeanDefinition(MEMCACHED_NAME, memcachedBeanDefinition.getBeanDefinition());*/
  }


}
