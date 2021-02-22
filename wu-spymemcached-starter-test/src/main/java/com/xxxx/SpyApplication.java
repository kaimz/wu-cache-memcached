package com.xxxx;

import com.wuwii.spring.boot.MemcachedPropertiesForConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kai.zhang
 * @date 2020-10-28 16:47
 */
@SpringBootApplication
@RestController
public class SpyApplication {

  private final MemcachedPropertiesForConfig memcachedPropertiesForConfig;

  public SpyApplication(MemcachedPropertiesForConfig memcachedPropertiesForConfig) {
    this.memcachedPropertiesForConfig = memcachedPropertiesForConfig;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpyApplication.class, args);
  }

  @GetMapping
  public String get() {
    String s = memcachedPropertiesForConfig.getEnable().toString();
    System.out.println(memcachedPropertiesForConfig.getUsername());
    System.out.println(s);
    return s;
  }
}
