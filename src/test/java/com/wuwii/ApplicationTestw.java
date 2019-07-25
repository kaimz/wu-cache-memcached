package com.wuwii;

import com.wuwii.spring.config.MemcachedConfig;
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
 * @date 2019-06-30 17:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MemcachedConfig.class)
public class ApplicationTestw {

  @Autowired
  private WuMemcached wuMemcached;


  @Test(timeout = 2000L)
  public void testGetKey() {
    String key = "test-key";
    String value = "wuwii";
    wuMemcached.set(key, 10, value);
    Assert.assertThat(wuMemcached.get(key), IsEqual.equalTo(value));
  }



}
