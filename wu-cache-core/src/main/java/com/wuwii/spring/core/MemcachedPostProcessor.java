package com.wuwii.spring.core;

import static com.wuwii.property.MemcacheBeanConstants.WU_FACTORY_BEAN_NAME;

import com.wuwii.spring.config.MemcachedClient;
import com.wuwii.spring.utils.BeanRegistrationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.PriorityOrdered;

/**
 * @author KronChan
 * @date 2019-07-25 20:04
 */
@Slf4j
public class MemcachedPostProcessor implements BeanDefinitionRegistryPostProcessor,
    PriorityOrdered {

  @Override
  public void postProcessBeanFactory(
      ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

  }

  @Override
  public int getOrder() {
    return LOWEST_PRECEDENCE;
  }

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
      throws BeansException {
    BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry, WU_FACTORY_BEAN_NAME,
        MemcachedClient.class);
    BeanRegistrationUtil
        .registerBeanDefinitionIfNotExists(registry, MemcachedBindingPostProcessor.class.getName(),
            MemcachedBindingPostProcessor.class);
  }
}
