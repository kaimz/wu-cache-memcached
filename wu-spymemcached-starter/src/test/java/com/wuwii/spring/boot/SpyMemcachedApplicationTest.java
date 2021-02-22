package com.wuwii.spring.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author kai.zhang
 * @date 2020/1/19 14:49
 */
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = SpyMemcachedApplicationTest.class)
@Configuration
@ComponentScan("com.wuwii.spring.boot")
public class SpyMemcachedApplicationTest {

  /*@Autowired
  private MemcachedProperties memcachedProperties;*/

  @Test
  public void test() {
    //System.out.println(memcachedProperties);
  }


}
