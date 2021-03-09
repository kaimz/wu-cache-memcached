package com.wuwii.spring.config;

import com.wuwii.spring.property.MemcacheBeanConstants;
import com.wuwii.spring.property.MemcachedKey;
import com.wuwii.spring.property.MemcachedProperties;
import com.wuwii.spring.spi.WuMemcachedStartHelper;
import com.wuwii.spring.utils.BeanObtainUtil;
import com.wuwii.spring.utils.ServiceBootstrap;
import java.util.concurrent.Callable;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.PriorityOrdered;

/**
 * @author kai.zhang
 * @date 2021-03-05 19:23
 */
@Data
public class MemcachedClient implements InitializingBean, BeanFactoryAware, WuMemcachedFactory,
    PriorityOrdered {

  /**
   * 启动类
   */
  private final static WuMemcachedStartHelper START_HELPER = ServiceBootstrap
      .loadPrimary(WuMemcachedStartHelper.class);
  /**
   * 执行工厂
   */
  private WuMemcachedFactory wuMemcachedFactory;
  /**
   * 连接属性
   */
  private MemcachedProperties memcachedProperties;
  private BeanFactory beanFactory;

  @Override
  public void afterPropertiesSet() throws Exception {
    // 初始化的时候连接属性
    if (this.memcachedProperties == null) {
      obtainConnectProperties();
    }
    // 二次校验
    if (this.memcachedProperties == null) {
      throw new NullPointerException(
          String.format("Bean [%s] not found.", MemcachedProperties.class.getName()));
    }
    this.wuMemcachedFactory = START_HELPER.of(memcachedProperties);
  }

  private void obtainConnectProperties() {
    this.memcachedProperties = BeanObtainUtil
        .getOptionalBean(beanFactory, MemcacheBeanConstants.MEMCACHED_PROPERTY_BEAN_NAME,
            MemcachedProperties.class);
  }

  @Override
  public int getOrder() {
    return LOWEST_PRECEDENCE;
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
  }

  @Override
  public Object get(MemcachedKey key) {
    return this.wuMemcachedFactory.get(key);
  }

  @Override
  public <T> T get(MemcachedKey key, Class<T> type) {
    return this.wuMemcachedFactory.get(key, type);
  }

  @Override
  public <T> T get(MemcachedKey key, Callable<T> valueLoader) {
    return this.wuMemcachedFactory.get(key, valueLoader);
  }

  @Override
  public void put(MemcachedKey key, Object value) {
    this.wuMemcachedFactory.put(key, value);
  }

  @Override
  public Object putIfAbsent(MemcachedKey key, Object value) {
    return this.wuMemcachedFactory.putIfAbsent(key, value);
  }

  @Override
  public void evict(MemcachedKey key) {
    this.wuMemcachedFactory.evict(key);
  }

  @Override
  public void clear() {
    this.wuMemcachedFactory.clear();
  }
}
