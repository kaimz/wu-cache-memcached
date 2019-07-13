package com.wuwii.spring.config;

import lombok.Data;

/**
 * @author KronChan
 * @date 2019-06-30 15:08
 */
@Data
class MemcachedProperties {

  private String host;

  private Integer port;

  private String username;

  private String password;
}
