package com.wuwii.spring;

/**
 * @author KronChan
 * @date 2019-08-21 21:55
 */

import com.wuwii.spring.annotation.EnableMemcached;
import com.wuwii.spring.annotation.WuMemcachedConfig;
import com.wuwii.spring.config.WuMemcachedFactory;
import com.wuwii.spring.property.MemcachedKey;
import org.hamcrest.core.Is;
import org.junit.Assert;
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
@EnableMemcached
public class SpringCacheApplicationTest {

  @Autowired
  private SpringCacheTest springCacheTest;
  @WuMemcachedConfig
  private WuMemcachedFactory memcached;
  static final String VALUE = "wuwii";
  private static String KEY = "12";
  private static final MemcachedKey MEMCACHED_KEY = new MemcachedKey(KEY);


  @Test
  public void testGet() {
    Assert.assertThat(springCacheTest.get(KEY), Is.is(VALUE));
    Assert.assertThat(springCacheTest.get(KEY), Is.is(VALUE));
  }

  @Test
  public void testFactoryGet() {
    memcached.put(MEMCACHED_KEY, VALUE);
    Object value = memcached.get(MEMCACHED_KEY);
    Assert.assertThat(value, Is.is(VALUE));
  }
}
