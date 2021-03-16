package com.wuwii.spring.handle.schemas;

import com.wuwii.property.MemcacheBeanConstants;
import com.wuwii.property.MemcachedProperties;
import com.wuwii.spring.cache.WuMemcachedManager;
import com.wuwii.spring.core.MemcachedPostProcessor;
import com.wuwii.spring.utils.BeanRegistrationUtil;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
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
    String addresses = element.getAttribute("addresses");
    String username = element.getAttribute("username");
    String password = element.getAttribute("password");
    bean.addPropertyValue("addresses", addresses);
    if (StringUtils.hasText(username)) {
      bean.addPropertyValue("username", username);
    }
    if (StringUtils.hasText(password)) {
      bean.addPropertyValue("password", password);
    }
    bean.addPropertyValue("timeout", element.getAttribute("timeout"));
    bean.addPropertyValue("disableSpringCache", element.getAttribute("disableSpringCache"));
  }

/*  @Override
  protected boolean shouldGenerateId() {
    return false;
  }*/

  @Override
  protected String resolveId(Element element, AbstractBeanDefinition definition,
      ParserContext parserContext) throws BeanDefinitionStoreException {
    return MemcacheBeanConstants.MEMCACHED_PROPERTY_BEAN_NAME;
  }

  @Override
  protected void registerBeanDefinition(BeanDefinitionHolder definition,
      BeanDefinitionRegistry registry) {
    // 注册 properties
    super.registerBeanDefinition(definition, registry);

    // 在注册 properties 后, 注册 MemcachedPostProcessor 完成 WuMemcached 注册
    BeanRegistrationUtil
        .registerBeanDefinitionIfNotExists(registry, MemcacheBeanConstants.POST_PROCESSOR_BEAN_NAME,
            MemcachedPostProcessor.class);
    String disableSpringCache = (String) definition.getBeanDefinition().getPropertyValues()
        .getPropertyValue("disableSpringCache")
        .getValue();
    if (Boolean.parseBoolean(disableSpringCache)) {
      BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry,
          WuMemcachedManager.class.getName(), WuMemcachedManager.class);
    }
  }
}
