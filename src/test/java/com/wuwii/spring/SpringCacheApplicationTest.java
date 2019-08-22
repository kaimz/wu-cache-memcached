package com.wuwii.spring;

/**
 * @author KronChan
 * @date 2019-08-21 21:55
 */

import com.wuwii.spring.annotation.EnableMemcached;
import com.wuwii.spring.annotation.WuMemcachedConfig;
import com.wuwii.spring.config.WuMemcachedFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringCacheApplicationTest.class)
@Configuration
@ComponentScan("com.wuwii.spring")
@EnableMemcached(addresses = "192.168.10.249:11211")
public class SpringCacheApplicationTest {

  @Autowired
  private SpringCacheTest springCacheTest;
  @WuMemcachedConfig
  private WuMemcachedFactory memcached;

  @Test
  public void testGet() {
    System.out.println(springCacheTest.get("12"));
    System.out.println(springCacheTest.get("12"));
  }

  @Test
  public void test() {
    System.out.println(memcached.get(12).get());
  }
}
