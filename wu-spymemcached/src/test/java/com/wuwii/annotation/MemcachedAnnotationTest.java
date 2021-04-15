package com.wuwii.annotation;

import com.wuwii.property.MemcachedKey;
import com.wuwii.property.SimpleMemcachedKey;
import com.wuwii.property.WuMemcachedFactory;
import com.wuwii.spring.core.WuMemcachedConfig;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author KronChan
 * @date 2019-07-25 19:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EnableMemCachedAnnotationTest.class})
public class MemcachedAnnotationTest {

  @WuMemcachedConfig
  private WuMemcachedFactory wuMemcached;

  @Test
  public void testGetKey() {
    if (wuMemcached == null) {
      Assert.fail();
    }
    MemcachedKey key = new SimpleMemcachedKey("test-key");
    String value = "wuwii";
    wuMemcached.put(key, value);
    Assert.assertThat(wuMemcached.get(key), IsEqual.equalTo(value));
    wuMemcached.evict(key);
  }

}
