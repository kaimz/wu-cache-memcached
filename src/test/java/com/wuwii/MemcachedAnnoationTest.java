package com.wuwii;

import com.wuwii.spring.config.WuMemcached;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author KronChan
 * @date 2019-07-25 19:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EnableMemCachedAnnotationTest.class})
public class MemcachedAnnoationTest {

  @Autowired(required = false)
  private WuMemcached wuMemcached;

  @Test
  public void test() {
    if (wuMemcached == null) {
      Assert.fail();
    }
    String key = "test-key";
    String value = "wuwii";
    wuMemcached.set(key, 10, value);
    Assert.assertThat(wuMemcached.get(key), IsEqual.equalTo(value));
  }

}
