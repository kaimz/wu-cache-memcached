package com.wuwii.spring.property;

import com.wuwii.spring.spi.WuMemcachedStartHelper;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.Callable;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.ConnectionFactory;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.support.SimpleValueWrapper;

/**
 * @author KronChan
 * @date 2019-07-04 19:57
 */
public class WuMemcached implements WuMemcachedStartHelper {

  private static final int TIMEOUT = 1000;

  private static final String NAME_SPACE = "memcached";

  private MemcachedClient memcachedClient;

  public WuMemcached() {
  }

  @Override
  public WuMemcached of(MemcachedProperties memcachedProperties) throws IOException {
    String username = memcachedProperties.getUsername();
    String password = memcachedProperties.getPassword();
    List<InetSocketAddress> addresses = AddrUtil
        .getAddresses(memcachedProperties.getAddresses());
    if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
      return new WuMemcached(new BinaryConnectionFactory(), addresses);
    }
    AuthDescriptor ad = AuthDescriptor.typical(username, password);
    return new WuMemcached(
        (new ConnectionFactoryBuilder()).setProtocol(ConnectionFactoryBuilder.Protocol.BINARY)
            .setAuthDescriptor(ad).build(), addresses);
  }

  private WuMemcached(ConnectionFactory cf, List<InetSocketAddress> addrs) throws IOException {
    this.memcachedClient = new MemcachedClient(cf, addrs);
  }

  @Override
  public int getOrder() {
    return LOWEST_PRECEDENCE;
  }

  @Override
  public String getName() {
    return NAME_SPACE;
  }

  @Override
  public Object getNativeCache() {
    return this.memcachedClient;
  }

  @Override
  public ValueWrapper get(Object key) {
    Object o = memcachedClient.get(key.toString());
    return o == null ? null : new SimpleValueWrapper(o);
  }

  @Override
  public <T> T get(Object key, Class<T> type) {
    Object o = memcachedClient.get(key.toString());
    return o == null ? null : (T) o;
  }

  @Override
  public <T> T get(Object key, Callable<T> valueLoader) {
    Object value = memcachedClient.get(key.toString());
    if (value == null) {
      try {
        return valueLoader.call();
      } catch (Exception e) {
        return null;
      }
    }
    return (T) value;
  }

  @Override
  public void put(Object key, Object value) {
    memcachedClient.add(key.toString(), TIMEOUT, value);
  }

  @Override
  public ValueWrapper putIfAbsent(Object key, Object value) {
    Object existValue = memcachedClient.get(key.toString());
    if (existValue == null) {
      put(key, value);
      return new SimpleValueWrapper(value);
    }
    return new SimpleValueWrapper(existValue);
  }

  @Override
  public void evict(Object key) {
    memcachedClient.delete(key.toString());
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException();
  }
}
