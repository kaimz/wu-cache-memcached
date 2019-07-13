package com.wuwii.spring.annoation;

import com.wuwii.spring.MemcachedConstant;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * @author KronChan
 * @date 2019-07-02 19:48
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MemcachedConfigRegistrar.class)
public @interface EnableMemcached {

  String address() default MemcachedConstant.DEFAULT_ADDRESS;
}
