package com.wuwii.annotation;

import com.wuwii.spring.annotation.WuMemcachedConfig;
import com.wuwii.spring.property.WuMemcached;
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
  private WuMemcached wuMemcached;

  @Test
  public void testGetKey() {
    if (wuMemcached == null) {
      Assert.fail();
    }
    String key = "test-key";
    String value = "wuwii";
    wuMemcached.set(key, 10, value);
    Assert.assertThat(wuMemcached.get(key), IsEqual.equalTo(value));
  }

}
