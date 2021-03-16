package com.wuwii.property;

import lombok.Data;

/**
 * @author KronChan
 * @date 2019-06-30 15:08
 */
@Data
public class MemcachedProperties {

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
}
