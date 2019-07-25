//package com.wuwii.spring.annotation;
//
//import com.wuwii.spring.MemcachedConstant;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.core.PriorityOrdered;
//import org.springframework.core.env.CompositePropertySource;
//import org.springframework.core.env.ConfigurableEnvironment;
//import org.springframework.core.env.Environment;
//
///**
// * @author KronChan
// * @date 2019-07-25 20:04
// */
//public class MemcachedProcess implements BeanFactoryPostProcessor, EnvironmentAware,
//    PriorityOrdered {
//
//
//  private ConfigurableEnvironment environment;
//
//  @Override
//  public void postProcessBeanFactory(
//      ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//    if (environment.getPropertySources().contains(MemcachedConstant.WU_MEMCACHED_PROPERTY_NAME)) {
//      return;
//    }
//    CompositePropertySource compositePropertySource = new CompositePropertySource(
//        MemcachedConstant.WU_MEMCACHED_PROPERTY_NAME);
//
//
//  }
//
//  @Override
//  public void setEnvironment(Environment environment) {
//    this.environment = (ConfigurableEnvironment) environment;
//  }
//
//  @Override
//  public int getOrder() {
//    return HIGHEST_PRECEDENCE;
//  }
//}
