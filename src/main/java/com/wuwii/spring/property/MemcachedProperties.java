package com.wuwii.spring.property;

import lombok.Data;

/**
 * @author KronChan
 * @date 2019-06-30 15:08
 */
@Data
public class MemcachedProperties {

  private String addresses;

  private String username;

  private String password;
}
