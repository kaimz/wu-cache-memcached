package com.wuwii.spring.handle.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author kai.zhang
 * @date 2021-03-09 19:54
 */
public class MemecachedAnnotationImportSelector implements ImportSelector {

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    return new String[]{MemcachedConfigRegistrar.class.getName()};
  }
}
