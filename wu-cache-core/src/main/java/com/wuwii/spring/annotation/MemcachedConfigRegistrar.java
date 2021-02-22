package com.wuwii.spring.annotation;

import com.wuwii.spring.cache.WuMemcachedManager;
import com.wuwii.spring.property.MemcachedProperties;
import com.wuwii.spring.utils.BeanRegistrationUtil;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
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
    String memcachedBeanName = MemcachedProperties.class.getName();
    if (beanDefinitionRegistry.containsBeanDefinition(memcachedBeanName)) {
      throw new BeanDefinitionValidationException(String
          .format("Bean: [%s] already in, could not create one more", memcachedBeanName));
    }
    AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationMetadata
        .getAnnotationAttributes(EnableMemcached.class.getName()));
    String addresses = attributes.getString("addresses");
    String username = attributes.getString("username");
    String password = attributes.getString("password");
    Map<String, Object> propertySourcesPlaceholderPropertyValues = new HashMap<>();
    propertySourcesPlaceholderPropertyValues.put("addresses", addresses);
    propertySourcesPlaceholderPropertyValues.put("username", username);
    propertySourcesPlaceholderPropertyValues.put("password", password);
    boolean disableSpringCache = attributes.getBoolean("disableSpringCache");
    propertySourcesPlaceholderPropertyValues
        .put("disableSpringCache", disableSpringCache);
    propertySourcesPlaceholderPropertyValues.put("timeout", attributes.get("timeout"));
    BeanRegistrationUtil
        .registerBeanDefinitionIfNotExists(beanDefinitionRegistry,
            MemcachedProperties.class.getName(),
            MemcachedProperties.class, propertySourcesPlaceholderPropertyValues);
    // 注册到 spring
    BeanRegistrationUtil.registerBeanDefinitionIfNotExists(beanDefinitionRegistry,
        MemcachedProcessor.class.getName(), MemcachedProcessor.class);
    // 自定义设置属性方式
    BeanRegistrationUtil.registerBeanDefinitionIfNotExists(beanDefinitionRegistry,
        MemcachedBindingPostProcessor.class.getName(), MemcachedBindingPostProcessor.class);
    // spring cache 管理
    if (disableSpringCache) {
      BeanRegistrationUtil.registerBeanDefinitionIfNotExists(beanDefinitionRegistry,
          WuMemcachedManager.class.getName(), WuMemcachedManager.class);
    }
  }
}
