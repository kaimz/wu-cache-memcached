package com.wuwii.spring.annotation;

import com.wuwii.spring.config.WuMemcachedFactory;
import com.wuwii.spring.property.MemcachedProperties;
import com.wuwii.spring.spi.WuMemcachedStartHelper;
import com.wuwii.spring.utils.ServiceBootstrap;
import java.io.IOException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.PriorityOrdered;

/**
 * @author KronChan
 * @date 2019-07-25 20:04
 */
@Slf4j
public class MemcachedProcessor implements BeanFactoryPostProcessor, InitializingBean,
    PriorityOrdered {

  private static WuMemcachedStartHelper helper = ServiceBootstrap
      .loadPrimary(WuMemcachedStartHelper.class);

  @Getter
  private static WuMemcachedFactory wuMemcachedFactory;

  private ConfigurableListableBeanFactory configurableListableBeanFactory;

  @Override
  public void postProcessBeanFactory(
      ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    this.configurableListableBeanFactory = configurableListableBeanFactory;
  }

  protected void processMemcachedFactory(
      ConfigurableListableBeanFactory configurableListableBeanFactory,
      MemcachedProperties properties) {
    log.debug("WuMemcached connect param: {}", properties);
    try {
      wuMemcachedFactory = helper.of(properties);
      configurableListableBeanFactory
          .registerSingleton(WuMemcachedFactory.class.getName(), wuMemcachedFactory);
    } catch (IOException e) {
      throw new BeanCreationException(
          String.format("Create class [%s], while memcached connect error in [%s]",
              WuMemcachedFactory.class.getName(), properties), e);
    }
  }

  @Override
  public int getOrder() {
    return LOWEST_PRECEDENCE;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    // 根据 memcached properties 注册 WuMemcached,保证了注入的顺序
    MemcachedProperties properties = configurableListableBeanFactory
        .getBean(MemcachedProperties.class);
    processMemcachedFactory(configurableListableBeanFactory, properties);
  }
}
