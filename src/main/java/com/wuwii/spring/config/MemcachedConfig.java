package com.wuwii.spring.config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.auth.AuthDescriptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author KronChan
 * @date 2019-06-27 23:23
 */
@Configuration
public class MemcachedConfig {

  /**
   * Inject memcached client while memcached properties exist
   *
   * @param memcachedProperties memcached properties
   * @return MemcachedClient bean
   * @throws IOException connect memcached client fail
   */
  @Bean
  @Conditional(MemcachedCondition.class)
  public WuMemcached wuMemcached(MemcachedProperties memcachedProperties)
      throws IOException {
    String username = memcachedProperties.getUsername();
    String password = memcachedProperties.getPassword();
    List<InetSocketAddress> addresses = AddrUtil
        .getAddresses(memcachedProperties.getHost() + ":" + memcachedProperties.getPort());
    if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
      return new WuMemcached(new BinaryConnectionFactory(), addresses);
    }
    AuthDescriptor ad = AuthDescriptor.typical(username, password);
    return new WuMemcached(
        (new ConnectionFactoryBuilder()).setProtocol(ConnectionFactoryBuilder.Protocol.BINARY)
            .setAuthDescriptor(ad).build(), addresses);
  }

}
