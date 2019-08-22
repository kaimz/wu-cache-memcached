package com.wuwii.spring.annotation;

import com.wuwii.spring.config.WuMemcachedFactory;
import com.wuwii.spring.property.MemcachedProperties;
import com.wuwii.spring.property.WuMemcached;
import com.wuwii.spring.spi.WuMemcachedStartHelper;
import com.wuwii.spring.utils.ServiceBootstrap;
import java.io.IOException;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.PriorityOrdered;

/**
 * @author KronChan
 * @date 2019-07-25 20:04
 */
public class MemcachedProcessor implements BeanFactoryPostProcessor, PriorityOrdered {

  private static WuMemcachedStartHelper helper = ServiceBootstrap
      .loadPrimary(WuMemcachedStartHelper.class);

  @Getter
  private static WuMemcachedFactory wuMemcachedFactory;

  @Override
  public void postProcessBeanFactory(
      ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    MemcachedProperties properties = configurableListableBeanFactory
        .getBean(MemcachedProperties.class);
    // 根据 memcached properties 注册 WuMemcached,保证了注入的顺序
    try {
      wuMemcachedFactory = helper.of(properties);
      configurableListableBeanFactory
          .registerSingleton(WuMemcached.class.getName(), wuMemcachedFactory);
    } catch (IOException e) {
      throw new BeanCreationException(
          String.format("Create class [%s], while memcached connect error in [%s]",
              WuMemcached.class.getName(), properties), e);
    }
  }

  @Override
  public int getOrder() {
    return LOWEST_PRECEDENCE;
  }
}
