package com.wuwii.spring.property;

/**
 * @author kai.zhang
 * @date 2020/2/10 22:42
 */
public class SimpleMemcachedKey extends AbstractMemcachedKey {

  private static final long serialVersionUID = 4339441372017444834L;

  public SimpleMemcachedKey(String keyElement) {
    super(keyElement);
  }

  @Override
  public String getKey() {
    return keyElement;
  }
}
