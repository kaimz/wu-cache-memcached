package com.wuwii.spring.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.util.ReflectionUtils;

public class BeanRegistrationUtil {


  public static boolean registerBeanDefinitionIfNotExists(BeanDefinitionRegistry registry,
      String beanName,
      Class<?> beanClass) {
    return registerBeanDefinitionIfNotExists(registry, beanName, beanClass, null);
  }

  public static boolean registerBeanDefinitionIfNotExists(BeanDefinitionRegistry registry,
      String beanName,
      Class<?> beanClass, Map<String, Object> extraPropertyValues) {
    if (registry.containsBeanDefinition(beanName)) {
      return false;
    }

    String[] candidates = registry.getBeanDefinitionNames();

    for (String candidate : candidates) {
      BeanDefinition beanDefinition = registry.getBeanDefinition(candidate);
      if (Objects.equals(beanDefinition.getBeanClassName(), beanClass.getName())) {
        return false;
      }
    }

    BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(beanClass)
        .getBeanDefinition();

    if (extraPropertyValues != null) {
      for (Map.Entry<String, Object> entry : extraPropertyValues.entrySet()) {
        beanDefinition.getPropertyValues().add(entry.getKey(), entry.getValue());
      }
    }

    registry.registerBeanDefinition(beanName, beanDefinition);

    return true;
  }

  public static boolean registerBeanDefinitionIfNotExists(BeanDefinitionRegistry registry,
      Object bean) {
    Class<?> beanClass = bean.getClass();
    String beanName = beanClass.getName();
    Map<String, Object> beanProperty = objToMap(bean);
    return registerBeanDefinitionIfNotExists(registry, beanName, beanClass, beanProperty);
  }

  private static Map<String, Object> objToMap(Object object) {
    Class<?> beanClass = object.getClass();
    Field[] fields = beanClass.getDeclaredFields();
    Map<String, Object> map = new HashMap<>(fields.length);
    for (Field field : fields) {
      ReflectionUtils.makeAccessible(field);
      Object value = ReflectionUtils.getField(field, object);
      if (value == null) {
        continue;
      }
      map.put(field.getName(), value);
    }
    return map;
  }



}
