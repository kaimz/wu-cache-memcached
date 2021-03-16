package com.wuwii.spring.core;

import com.wuwii.property.MemcacheBeanConstants;
import com.wuwii.property.WuMemcachedFactory;
import java.lang.reflect.Field;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

/**
 * 后置处理器
 * @author KronChan
 * @date 2019-07-29 23:00
 */
public class MemcachedBindingPostProcessor implements BeanPostProcessor, BeanFactoryPostProcessor,
    PriorityOrdered {

  private WuMemcachedFactory wuMemcached;

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
      if (wuMemcached == null) {
        throw new NoSuchBeanDefinitionException(
                String.format("Bean: [%s] is not found.", WuMemcachedFactory.class.getName()));
      }
      ReflectionUtils.makeAccessible(field);
      ReflectionUtils.setField(field, bean, wuMemcached);
    }
  }

  @Override
  public int getOrder() {
    return LOWEST_PRECEDENCE;
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
      throws BeansException {
    this.wuMemcached = beanFactory
        .getBean(MemcacheBeanConstants.WU_FACTORY_BEAN_NAME, WuMemcachedFactory.class);
  }
}
