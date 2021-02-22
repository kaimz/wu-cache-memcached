package com.wuwii.xml;

import com.wuwii.spring.annotation.WuMemcachedConfig;
import com.wuwii.spring.property.MemcachedKey;
import com.wuwii.spring.property.SimpleMemcachedKey;
import com.wuwii.spring.property.WuSpyMemcached;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author KronChan
 * @date 2019-07-29 09:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:memcached-spring.xml")
public class XmlTest {

  @WuMemcachedConfig
  private WuSpyMemcached wuSpyMemcached;


  @Test(timeout = 2000L)
  public void testGetKey() {
    if (wuSpyMemcached == null) {
      Assert.fail();
    }
    MemcachedKey key = new SimpleMemcachedKey("test-key");
    String value = "wuwii";
    wuSpyMemcached.put(key, value);
    Assert.assertThat(wuSpyMemcached.get(key), IsEqual.equalTo(value));
  }

}
