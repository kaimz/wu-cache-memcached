package com.wuwii.spring.config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import lombok.experimental.Delegate;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.ConnectionFactory;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author KronChan
 * @date 2019-07-04 19:57
 */
public class WuMemcached {

  @Delegate
  private MemcachedClient memcachedClient;

  public static WuMemcached of(MemcachedProperties memcachedProperties) throws IOException {
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

  public WuMemcached(InetSocketAddress... ia) throws IOException {
    this.memcachedClient = new MemcachedClient(ia);
  }

  public WuMemcached(List<InetSocketAddress> addrs) throws IOException {
    this.memcachedClient = new MemcachedClient(addrs);
  }

  public WuMemcached(ConnectionFactory cf, List<InetSocketAddress> addrs) throws IOException {
    this.memcachedClient = new MemcachedClient(cf, addrs);
  }

}
