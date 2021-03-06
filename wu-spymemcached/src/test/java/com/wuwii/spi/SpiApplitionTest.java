package com.wuwii.spi;

import com.wuwii.property.WuMemcachedFactory;
import com.wuwii.spring.config.MemcachedClient;
import com.wuwii.spring.handle.annotation.EnableMemcached;
import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ReflectionUtils;

/**
 * @author KronChan
 * @date 2019-08-22 21:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpiApplitionTest.class)
@Configuration
@ComponentScan("com.wuwii.spi")
@EnableMemcached(addresses = "192.168.10.249:11211,127.0.0.1:11211")
public class SpiApplitionTest {

  @Autowired
  private WuMemcachedFactory wuMemcachedFactory;

  @Test
  public void testSpi() {
    Field field = ReflectionUtils.findField(MemcachedClient.class, "START_HELPER");
    ReflectionUtils.makeAccessible(field);
    Object helper = ReflectionUtils.getField(field, wuMemcachedFactory);
    Assert.assertTrue(helper instanceof WuSpyMemcachedSpiTest);
  }
}
