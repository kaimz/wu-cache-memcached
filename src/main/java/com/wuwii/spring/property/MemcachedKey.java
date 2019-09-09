package com.wuwii.spring.property;

import java.io.Serializable;

/**
 * @author KronChan
 * @date 2019/9/9 12:36 下午
 */
public class MemcachedKey implements Serializable {

  private static final long serialVersionUID = -4758557543093739416L;

  private final String keyElement;

  private String prefix;

  /**
   * 中间介质
   */
  private String mediumKey = ".";

  public MemcachedKey(String keyElement) {
    this.keyElement = keyElement;
  }

  public MemcachedKey(String keyElement, String prefix) {
    this.keyElement = keyElement;
    this.prefix = prefix;
  }

  public MemcachedKey(String keyElement, String prefix, String mediumKey) {
    this.keyElement = keyElement;
    this.prefix = prefix;
    this.mediumKey = mediumKey;
  }

  public String getKey() {
    if (prefix == null || prefix.trim().length() == 0) {
      return keyElement;
    }
    return prefix + mediumKey + keyElement;
  }

  @Override
  public String toString() {
    return getKey();
  }
}
