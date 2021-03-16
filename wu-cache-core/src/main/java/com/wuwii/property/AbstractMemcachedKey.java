package com.wuwii.property;

import java.io.Serializable;

/**
 * @author kai.zhang
 * @date 2020/2/10 22:27
 */
public abstract class AbstractMemcachedKey implements MemcachedKey, Serializable {

  private static final long serialVersionUID = 7094606333493527002L;

  protected final String keyElement;

  public AbstractMemcachedKey(String keyElement) {
    this.keyElement = keyElement;
  }

  @Override
  public String toString() {
    return getKey();
  }
}
