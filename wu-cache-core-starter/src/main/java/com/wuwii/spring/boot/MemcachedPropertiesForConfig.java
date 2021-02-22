package com.wuwii.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author kai.zhang
 * @date 2020/1/17 19:55
 */
@ConfigurationProperties(prefix = "wu.memcached")
public class MemcachedPropertiesForConfig {

  private Boolean enable = false;

  private String addresses = "127.0.0.1:11211";

  private String username;

  private String password;

  /**
   * 默认缓存时间一天
   */
  private int timeout = 24 * 60 * 1000;

  /**
   * 不启用spring注解事务,默认为 true
   */
  private boolean disableSpringCache = true;

  public Boolean getEnable() {
    return enable;
  }

  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  public String getAddresses() {
    return addresses;
  }

  public void setAddresses(String addresses) {
    this.addresses = addresses;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  public boolean isDisableSpringCache() {
    return disableSpringCache;
  }

  public void setDisableSpringCache(boolean disableSpringCache) {
    this.disableSpringCache = disableSpringCache;
  }
}
