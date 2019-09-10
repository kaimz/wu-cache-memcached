### spymemcached in spring

[![Build Status](https://travis-ci.com/kaimz/wu-cache-memcached.svg?branch=master)](https://travis-ci.com/kaimz/wu-cache-memcached)
[![codecov](https://codecov.io/gh/kaimz/wu-cache-memcached/branch/master/graph/badge.svg)](https://codecov.io/gh/kaimz/wu-cache-memcached)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/895344dda9ec4b69af29b56514b9334a)](https://www.codacy.com/manual/kaimz/wu-cache-memcached?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=kaimz/wu-cache-memcached&amp;utm_campaign=Badge_Grade)

#### 项目学习使用

主要是学习 memcached 在 spring 项目的灵活配置和使用。

目的：

1. 掌握 spring 扩展配置的方法技巧
2. memcached 缓存在配置在 spring cache 中

##### By

* java 8

* spring context 4.3
* spymemcached 2.7

##### 了解

看代码之前需要了解的功课

* spring bean 引用组件
  1. bean config
  2. `ImportBeanDefinitionRegistrar`
  3. [xml namespace schema extension](https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/xml-custom.html)

* spring 扩展
  1. **BeanPostProcessor**： 初始化bean之前和之后，每次bean 初始化都会调用，参考`AutowiredAnnotationBeanPostProcessor`
  2. **BeanFactoryPostProcessor**: 初始化bean 之前，在 `BeanPostProcessor` 之前，可用于更改bean 的属性，只会执行一次。
  3. **InstantiationAwareBeanPostProcessor**：实例化bean 之前和之后，每个bean 实例化都会执行一次。
  4. **FactoryBean**:  自己控制生成bean。
  5. **Aware**：感知，回调其 set 方法将相应的参数设置给该 bean。
  6. **InitialingBean**: 初始化结束
  7. **DisposableBean**：bean 销毁

* spring cache:
  1. **CacheManager**，参考 `EhCacheCacheManager` 实现。
  2. **Cache**, 参考 `RedisCache` 实现。

#### Usage

#### 扩展
1. 可以使用 spi机制,实现 `WuMemcachedStartHelper` 接口,可以对接上 `XMemcached`.

备注：

1. spring 扩展

   1. xml 扩展
   2. 注解扩展

   主要参考 https://github.com/ctripcorp/apollo