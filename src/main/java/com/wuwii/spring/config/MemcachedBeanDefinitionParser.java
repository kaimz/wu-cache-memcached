package com.wuwii.spring.config;

import com.wuwii.spring.annotation.MemcachedProcessor;
import com.wuwii.spring.annotation.MemcachedSourceProcessor;
import com.wuwii.spring.property.MemcachedProperties;
import com.wuwii.spring.utils.BeanRegistrationUtil;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author KronChan
 * @date 2019-06-30 15:20
 */
public class MemcachedBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

  @Override
  protected Class getBeanClass(Element element) {
    return MemcachedProperties.class;
  }

  @Override
  protected void doParse(Element element, BeanDefinitionBuilder bean) {
    String host = element.getAttribute("host");
    String port = element.getAttribute("port");
    String username = element.getAttribute("username");
    String password = element.getAttribute("password");
    bean.addPropertyValue("host", host);
    bean.addPropertyValue("port", port);
    if (StringUtils.hasText(username)) {
      bean.addPropertyValue("username", username);
    }
    if (StringUtils.hasText(password)) {
      bean.addPropertyValue("password", password);
    }
  }

  @Override
  protected void registerBeanDefinition(BeanDefinitionHolder definition,
      BeanDefinitionRegistry registry) {
    // 注册 properties
    super.registerBeanDefinition(definition, registry);
    // 在注册 properties 后, 注册 MemcachedProcessor 完成 WuMemcached 注册
    BeanRegistrationUtil
        .registerBeanDefinitionIfNotExists(registry, MemcachedProcessor.class.getName(),
            MemcachedProcessor.class);
    BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry,
        MemcachedSourceProcessor.class.getName(), MemcachedSourceProcessor.class);
  }
}
