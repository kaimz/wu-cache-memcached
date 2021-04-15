package com.xxxx.test;

import com.wuwii.property.SimpleMemcachedKey;
import com.wuwii.property.WuMemcachedFactory;
import com.wuwii.spring.core.WuMemcachedConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kai.zhang
 * @date 2021-04-14 19:33
 */
@RestController
public class TestController {

  @WuMemcachedConfig
  private WuMemcachedFactory memmmclient;
  @Autowired
  private WuMemcachedFactory memcachedFactory;

  @GetMapping
  public String get() {
    SimpleMemcachedKey key = new SimpleMemcachedKey("a-test");
    memmmclient.put(key, "test");
    System.out.println(memmmclient.get(key));
    memmmclient.evict(key);
    return "12";
  }

  @GetMapping("/test")
  public String test() {
    SimpleMemcachedKey key = new SimpleMemcachedKey("a-test");
    memcachedFactory.put(key, "test");
    System.out.println(memmmclient.get(key));
    memcachedFactory.evict(key);
    return "test";
  }
}
