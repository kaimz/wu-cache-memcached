package com.wuwii.property.support;

import com.wuwii.property.MemcachedKey;
import com.wuwii.property.MemcachedProperties;
import com.wuwii.property.WuMemcachedFactory;
import com.wuwii.spi.WuMemcachedStartHelper;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.auth.AuthInfo;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author KronChan
 * @date 2019/10/11 12:40 下午
 */
public class WuXmemcached implements WuMemcachedStartHelper, WuMemcachedFactory {

  private static final Logger log = LoggerFactory.getLogger(WuXmemcached.class);
  private int timeout;

  private MemcachedClient memcachedClient;

  public WuXmemcached() {
  }

  private WuXmemcached(int timeout, MemcachedClient memcachedClient) {
    this.timeout = timeout;
    this.memcachedClient = memcachedClient;
  }

  @Override
  public WuMemcachedFactory of(MemcachedProperties properties) throws IOException {
    List<InetSocketAddress> addresses = AddrUtil.getAddresses(properties.getAddresses());
    MemcachedClientBuilder builder = new XMemcachedClientBuilder(addresses);
    builder.setFailureMode(true);
    builder.setCommandFactory(new BinaryCommandFactory());
    if (properties.getUsername() != null && properties.getPassword() != null) {
      AuthInfo authInfo = AuthInfo.plain(properties.getUsername(), properties.getPassword());
      Map<InetSocketAddress, AuthInfo> authInfoMap = new HashMap<>(addresses.size());
      for (InetSocketAddress address : addresses) {
        authInfoMap.put(address, authInfo);
      }
      builder.setAuthInfoMap(authInfoMap);
    }
    return new WuXmemcached(properties.getTimeout(), builder.build());
  }

  @Override
  public Object get(MemcachedKey key) {
    try {
      return memcachedClient.get(key.getKey());
    } catch (TimeoutException | InterruptedException | MemcachedException e) {
      log.error("xmemcached get key failed.", e);
    }
    return null;
  }

  @Override
  public <T> T get(MemcachedKey key, Class<T> type) {
    Object o = get(key);
    return o == null ? null : (T) o;
  }

  @Override
  public <T> T get(MemcachedKey key, Callable<T> valueLoader) {
    Object o = get(key);
    try {
      return o == null ? valueLoader.call() : (T) o;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public void put(MemcachedKey key, Object value) {
    try {
      memcachedClient.set(key.toString(), timeout, value);
    } catch (TimeoutException | InterruptedException | MemcachedException e) {
      log.error("xmemcached set key failed.", e);
    }
  }

  @Override
  public Object putIfAbsent(MemcachedKey key, Object value) {
    try {
      Object source = memcachedClient.get(key.getKey());
      if (source == null) {
        put(key, value);
        return value;
      }
      return source;
    } catch (TimeoutException | InterruptedException | MemcachedException e) {
      log.error("xmemcached get key failed.", e);
      return null;
    }
  }

  @Override
  public void evict(MemcachedKey key) {
    try {
      memcachedClient.delete(key.getKey());
    } catch (TimeoutException | InterruptedException | MemcachedException e) {
      log.error("xmemcached get key failed.", e);
    }
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException("clear method is not supported.");
  }

  @Override
  public int getOrder() {
    return LOWEST_PRECEDENCE;
  }
}
