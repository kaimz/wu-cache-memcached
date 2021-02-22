package com.wuwii.spring.boot;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author kai.zhang
 * @date 2020-10-15 18:11
 */
public class MemcachedConfigPropertiesImportSelector implements ImportSelector {

  private static final String[] IMPORTS = {ConfigMemcachedProcessor.class.getName()};

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    return IMPORTS;
  }
}
