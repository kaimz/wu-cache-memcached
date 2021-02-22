package com.wuwii.spring.property;

/**
 * @author KronChan
 * @date 2019/9/9 12:36 下午
 */
public class WithPrefixMemcachedKey extends AbstractMemcachedKey {

  private static final long serialVersionUID = -7454736237320224000L;

  private String prefix;

  /**
   * 中间介质
   */
  private String mediumKey = ".";

  public WithPrefixMemcachedKey(String keyElement, String prefix) {
    super(keyElement);
    this.prefix = prefix;
  }

  public WithPrefixMemcachedKey(String keyElement, String prefix, String mediumKey) {
    super(keyElement);
    this.prefix = prefix;
    this.mediumKey = mediumKey;
  }

  @Override
  public String getKey() {
    return prefix + mediumKey + keyElement;
  }

}
