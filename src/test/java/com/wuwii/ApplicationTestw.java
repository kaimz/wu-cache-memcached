package com.wuwii;

import com.wuwii.spring.Testw;
import com.wuwii.spring.config.MemcachedConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author KronChan
 * @date 2019-06-30 17:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MemcachedConfig.class)
@Import(MemcachedConfig.class)
public class ApplicationTestw {

  //  @Autowired
  // private MemcachedClient memcachedClient;
  @Autowired
  private Testw testw;

//    @Test(timeout = 2000L)
//    public void testGetKey() {
//        String key = "test-key";
//        String value = "wuwii";
//        memcachedClient.set(key, 10, value);
//        Assert.assertThat(memcachedClient.get(key), IsEqual.equalTo(value));
//    }

  @Test
  public void test() {
    System.out.println(testw);
  }


}
