package com.wuwii.spring.config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import lombok.experimental.Delegate;
import net.spy.memcached.ConnectionFactory;
import net.spy.memcached.MemcachedClient;

/**
 * @author KronChan
 * @date 2019-07-04 19:57
 */
public class WuMemcached {

  @Delegate
  private MemcachedClient memcachedClient;

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
