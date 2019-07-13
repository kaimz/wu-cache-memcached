package com.wuwii.spring.config;

import com.wuwii.spring.Testw;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author KronChan
 * @date 2019-06-27 23:23
 */
@Configuration
@ComponentScan(basePackageClasses = {MemcachedConfig.class})
public class MemcachedConfig {

  /**
   * Inject memcached client while memcached properties exist
   *
   * @param memcachedProperties memcached properties
   * @return MemcachedClient bean
   * @throws IOException connect memcached client fail
   */
  @Bean(autowire = Autowire.BY_TYPE)
  @Conditional(MemcachedCondition.class)
  public MemcachedClient memcachedClient(MemcachedProperties memcachedProperties)
      throws IOException {
    String username = memcachedProperties.getUsername();
    String password = memcachedProperties.getPassword();
    List<InetSocketAddress> addresses = AddrUtil
        .getAddresses(memcachedProperties.getHost() + ":" + memcachedProperties.getPort());
    if (username == null || password == null) {
      return new MemcachedClient(new BinaryConnectionFactory(), addresses);
    }
    AuthDescriptor ad = AuthDescriptor.typical(username, password);
    return new MemcachedClient(
        (new ConnectionFactoryBuilder()).setProtocol(ConnectionFactoryBuilder.Protocol.BINARY)
            .setAuthDescriptor(ad).build(), addresses);
  }

  @Bean
  public Testw testw() {
    Testw testw = new Testw();
    testw.setName("test");
    return testw;
  }
}
