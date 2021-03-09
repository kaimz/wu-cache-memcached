package com.wuwii.spring.utils;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 * @author kai.zhang
 * @date 2021-03-09 19:19
 */
public class BeanObtainUtil {

  private BeanObtainUtil() {
  }


  /**
   * @return 获取 bean 对象，如果无次Bean，返回 {@code null}
   */
  public static <T> T getOptionalBean(BeanFactory beanFactory, String name, Class<T> type) {
    try {
      return beanFactory.getBean(name, type);
    } catch (NoSuchBeanDefinitionException ex) {
      return null;
    }
  }

}
