package com.wuwii.spring.config;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author KronChan
 * @date 2019-06-30 16:04
 */
public class MemcachedCondition implements Condition {

  @Override
  public boolean matches(ConditionContext conditionContext,
      AnnotatedTypeMetadata annotatedTypeMetadata) {
    try {
      conditionContext.getBeanFactory().getBean(MemcachedProperties.class);
      return true;
    } catch (BeansException e) {
      return false;
    }
  }
}
