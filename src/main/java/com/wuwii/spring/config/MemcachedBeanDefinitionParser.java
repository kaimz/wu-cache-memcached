package com.wuwii.spring.config;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
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

}
