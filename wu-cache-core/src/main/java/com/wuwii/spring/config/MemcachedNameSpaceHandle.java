package com.wuwii.spring.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author KronChan
 * @date 2019-06-30 15:19
 */
public class MemcachedNameSpaceHandle extends NamespaceHandlerSupport {

  @Override
  public void init() {
    this.registerBeanDefinitionParser("memcached", new MemcachedBeanDefinitionParser());
  }
}
