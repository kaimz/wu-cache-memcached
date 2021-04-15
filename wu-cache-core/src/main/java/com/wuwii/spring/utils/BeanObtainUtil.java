package com.wuwii.spring.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 * @author kai.zhang
 * @date 2021-03-09 19:19
 */
public class BeanObtainUtil {

  private static final Logger logger = Logger.getLogger(BeanObtainUtil.class.getName());

  private BeanObtainUtil() {
  }


  /**
   * @return 获取 bean 对象，如果无次Bean，返回 {@code null}
   */
  public static <T> T getOptionalBean(BeanFactory beanFactory, String name, Class<T> type) {
    try {
      return beanFactory.getBean(name, type);
    } catch (NoSuchBeanDefinitionException ex) {
      if (logger.isLoggable(Level.WARNING)) {
        logger.warning("Can not find bean:" + name);
      }
      return null;
    }
  }

}
