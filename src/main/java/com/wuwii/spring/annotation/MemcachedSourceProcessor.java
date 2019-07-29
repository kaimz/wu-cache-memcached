package com.wuwii.spring.annotation;

import com.wuwii.spring.property.WuMemcached;
import java.lang.reflect.Field;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

/**
 * @author KronChan
 * @date 2019-07-29 23:00
 */
public class MemcachedSourceProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
    Class clazz = o.getClass();
    processFields(o, clazz.getDeclaredFields());
    return o;
  }

  @Override
  public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
    return o;
  }

  private void processFields(Object bean, Field[] declaredFields) {
    for (Field field : declaredFields) {
      WuMemcachedConfig annotation = AnnotationUtils.getAnnotation(field, WuMemcachedConfig.class);
      if (annotation == null) {
        continue;
      }
      if (WuMemcachedConfig.class.isAssignableFrom(field.getType())) {
        return;
      }
      ReflectionUtils.makeAccessible(field);
      WuMemcached wuMemcached = MemcachedProcessor.getWuMemcached();
      ReflectionUtils.setField(field, bean, wuMemcached);
    }
  }
}
