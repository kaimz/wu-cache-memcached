package com.wuwii.annotation;

import com.wuwii.spring.annotation.EnableMemcached;
import org.springframework.context.annotation.Configuration;

/**
 * @author KronChan
 * @date 2019-07-25 19:38
 */
@Configuration
@EnableMemcached(addresses = "192.168.10.249:11211")
public class EnableMemCachedAnnotationTest {

}
