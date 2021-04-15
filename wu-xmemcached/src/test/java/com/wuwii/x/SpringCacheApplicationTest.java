package com.wuwii.x;

/**
 * @author KronChan
 * @date 2019-08-21 21:55
 */

import com.wuwii.property.WithPrefixMemcachedKey;
import com.wuwii.property.WuMemcachedFactory;
import com.wuwii.spring.core.WuMemcachedConfig;
import com.wuwii.spring.handle.annotation.EnableMemcached;
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
@ComponentScan("com.wuwii.x")
@EnableMemcached
public class SpringCacheApplicationTest {

  static final String VALUE = "wuwii";
  private static String KEY = "12";
  private static final WithPrefixMemcachedKey MEMCACHED_KEY = new WithPrefixMemcachedKey("test",
      KEY);
  @Autowired
  private SpringCacheTest springCacheTest;
  @WuMemcachedConfig
  private WuMemcachedFactory memcached;

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
