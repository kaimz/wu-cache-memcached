package com.wuwii.spring.handle.annotation;

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
@Import(MemecachedAnnotationImportSelector.class)
public @interface EnableMemcached {

  String addresses() default "127.0.0.1:11211";

  String username() default "";

  String password() default "";

  int timeout() default 24 * 60 * 1000;

  boolean disableSpringCache() default true;

}
