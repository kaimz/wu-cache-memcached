package com.wuwii;

import com.wuwii.property.MemcachedKey;
import com.wuwii.property.SimpleMemcachedKey;
import com.wuwii.property.WuMemcachedFactory;
import com.wuwii.spring.boot.WuSpyMemcachedAutoConfiguration;
import com.wuwii.spring.core.WuMemcachedConfig;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author kai.zhang
 * @date 2021-04-16 18:54
 */
@RunWith(SpringRunner.class)//单元测试使用SpringBoot的驱动器
@SpringBootTest(classes = {WuSpyMemcachedAutoConfiguration.class})
public class TestWuSpyMemcachedAutoConfiguration {

  @WuMemcachedConfig
  WuMemcachedFactory wuMemcached;

  @Test
  public void testKey() {
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
