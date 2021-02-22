package com.wuwii;

import com.wuwii.spring.boot.MemcachedPropertiesForConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kai.zhang
 * @date 2020/1/19 16:18
 */
@SpringBootApplication
public class SpyMemcachedApplication {

  @Autowired
  private MemcachedPropertiesForConfig memcachedPropertiesForConfig;

  public static void main(String[] args) {
    SpringApplication.run(SpyMemcachedApplication.class, args);
  }

}
