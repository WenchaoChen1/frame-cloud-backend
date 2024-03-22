// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtils {

  private RedisTemplate<Object, Object> redisTemplate;

  public RedisUtils(RedisTemplate<Object, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  /**
   * Specifies the cache expiration time
   *
   * @param key key
   * @param time time (seconds)
   */
  public boolean expire(String key, long time) {
    try {
      if (time > 0) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }

    return true;
  }

  /**
   * Specifies the cache expiration time
   *
   * @param key key
   * @param time time (seconds)
   * @param timeUnit unit
   */
  public boolean expire(String key, long time, TimeUnit timeUnit) {
    try {
      if (time > 0) {
        redisTemplate.expire(key, time, timeUnit);
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }

    return true;
  }

  /**
   * Get the expiration time according to the key
   *
   * @param key key cannot be null
   * @return time (seconds) return 0 means it is permanent
   */
  public long getExpire(Object key) {
    return redisTemplate.getExpire(key, TimeUnit.SECONDS);
  }

  /**
   * Find matching key
   *
   * @param pattern key
   * @return /
   */
  public List<String> scan(String pattern) {
    ScanOptions options = ScanOptions.scanOptions().match(pattern).build();
    RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
    RedisConnection rc = Objects.requireNonNull(factory).getConnection();
    Cursor<byte[]> cursor = rc.scan(options);

    List<String> result = new ArrayList<>();
    while (cursor.hasNext()) {
      result.add(new String(cursor.next()));
    }
//
//    try {
//      RedisConnectionUtils.releaseConnection(rc, factory, false);
//    } catch (Exception e) {
//      log.error(e.getMessage(), e);
//    }

    return result;
  }

  /**
   * Pagination query key
   *
   * @param patternKey key
   * @param page page number
   * @param size number of pages
   * @return /
   */
  public List<String> findKeysForPage(String patternKey, int page, int size) {
    ScanOptions options = ScanOptions.scanOptions().match(patternKey).build();
    RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
    RedisConnection rc = Objects.requireNonNull(factory).getConnection();
    Cursor<byte[]> cursor = rc.scan(options);
    List<String> result = new ArrayList<>(size);
    int tmpIndex = 0;
    int fromIndex = page * size;
    int toIndex = page * size + size;

    while (cursor.hasNext()) {
      if (tmpIndex >= fromIndex && tmpIndex < toIndex) {
        result.add(new String(cursor.next()));
        tmpIndex++;
        continue;
      }

      //After obtaining the data that meets the conditions, you can exit
      if (tmpIndex >= toIndex) {
        break;
      }

      tmpIndex++;
      cursor.next();
    }
//
//    try {
//      RedisConnectionUtils.releaseConnection(rc, factory, false);
//    } catch (Exception e) {
//      log.error(e.getMessage(), e);
//    }

    return result;
  }

  /**
   * Determine whether the key exists
   *
   * @param key key
   * @return true exists false does not exist
   */
  public boolean hasKey(String key) {
    try {
      return redisTemplate.hasKey(key);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }

  /**
   * delete cache
   *
   * @param key can pass one value or more
   */
  public void del(String... keys) {
    if (keys != null && keys.length > 0) {
      if (keys.length == 1) {
        boolean result = redisTemplate.delete(keys[0]);
        log.info("--------------------------------------------");
        log.info("delete cache：" + keys[0] + "，result：" + result);
        log.info("--------------------------------------------");
      } else {
        Set<Object> keySet = new HashSet<>();
        for (String key : keys) {
          keySet.addAll(redisTemplate.keys(key));
        }

        long count = redisTemplate.delete(keySet);
        log.info("--------------------------------------------");
        log.info("Successfully deleted cache：" + keySet);
        log.info("Number of cache deletes：" + count );
        log.info("--------------------------------------------");
      }
    }
  }

  // ============================String=============================

  /**
   * Ordinary cache acquisition
   *
   * @param key key
   * @return value
   */
  public Object get(String key) {
    return key == null ? null : redisTemplate.opsForValue().get(key);
  }

  /**
   * Batch acquisition
   *
   * @param keys
   * @return
   */
  public List<Object> multiGet(List<String> keys) {
    return redisTemplate.opsForValue().multiGet(Collections.singleton(keys));
  }

  /**
   * Ordinary cache put
   *
   * @param key key
   * @param value value
   * @return true success false failure
   */
  public boolean set(String key, Object value) {
    try {
      redisTemplate.opsForValue().set(key, value);
      return true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }

  /**
   * Ordinary cache is put in and set time
   *
   * @param key key
   * @param value value
   * @param time time (seconds) time must be greater than 0, if time is less than or equal to 0, it will be set indefinitely
   * @return true success false failure
   */
  public boolean set(String key, Object value, long time) {
    try {
      if (time > 0) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
      } else {
        set(key, value);
      }
      return true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }

  /**
   * Ordinary cache is put in and set time
   *
   * @param key key
   * @param value value
   * @param time time
   * @param timeUnit type
   * @return true success false failure
   */
  public boolean set(String key, Object value, long time, TimeUnit timeUnit) {
    try {
      if (time > 0) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
      } else {
        set(key, value);
      }
      return true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }

  // ================================Map=================================

  /**
   * HashGet
   *
   * @param key key cannot be null
   * @param item item cannot be null
   * @return value
   */
  public Object hget(String key, String item) {
    return redisTemplate.opsForHash().get(key, item);
  }

  /**
   * Get all key values corresponding to hashKey
   *
   * @param key key
   * @return corresponding multiple key values
   */
  public Map<Object, Object> hmget(String key) {
    return redisTemplate.opsForHash().entries(key);

  }

  /**
   * HashSet
   *
   * @param key key
   * @param map corresponds to multiple key values
   * @return true success false failure
   */
  public boolean hmset(String key, Map<String, Object> map) {
    try {
      redisTemplate.opsForHash().putAll(key, map);
      return true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }

  /**
   * HashSet and set the time
   *
   * @param key key
   * @param map corresponds to multiple key values
   * @param time time (seconds)
   * @return true success false failure
   */
  public boolean hmset(String key, Map<String, Object> map, long time) {
    try {
      redisTemplate.opsForHash().putAll(key, map);
      if (time > 0) {
        expire(key, time);
      }

      return true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }
  /**
   * Put data into a hash table, if it does not exist, it will be created
   *
   * @param key key
   * @param item item
   * @param value value
   * @return true success false failure
   */
  public boolean hset(String key, String item, Object value) {
    try {
      redisTemplate.opsForHash().put(key, item, value);
      return true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }

  /**
   * Put data into a hash table, if it does not exist, it will be created
   *
   * @param key key
   * @param item item
   * @param value value
   * @param time Time (seconds) Note: If the existing hash table has time, the original time will be replaced here
   * @return true success false failure
   */
  public boolean hset(String key, String item, Object value, long time) {
    try {
      redisTemplate.opsForHash().put(key, item, value);
      if (time > 0) {
        expire(key, time);
      }

      return true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }

  /**
   * Delete the value in the hash table
   *
   * @param key key cannot be null
   * @param item item can be multiple and cannot be null
   */
  public void hdel(String key, Object... item) {
    redisTemplate.opsForHash().delete(key, item);
  }

  /**
   * Determine whether there is a value for this item in the hash table
   *
   * @param key key cannot be null
   * @param item item cannot be null
   * @return true exists false does not exist
   */
  public boolean hHasKey(String key, String item) {
    return redisTemplate.opsForHash().hasKey(key, item);
  }

  /**
   * hash increment If it does not exist, it will create one and return the newly added value
   *
   * @param key key
   * @param item item
   * @param by how much to increase (greater than 0)
   * @return
   */
  public double hincr(String key, String item, double by) {
    return redisTemplate.opsForHash().increment(key, item, by);
  }

  /**
   * hash decrement
   *
   * @param key key
   * @param item item
   * @param by to reduce (less than 0)
   * @return
   */
  public double hdecr(String key, String item, double by) {
    return redisTemplate.opsForHash().increment(key, item, -by);
  }

  // ============================set=============================

  /**
   * Get all the values in the Set according to the key
   *
   * @param key key
   * @return
   */
  public Set<Object> sGet(String key) {
    try {
      return redisTemplate.opsForSet().members(key);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }

  /**
   * Query from a set according to value, whether it exists
   *
   * @param key key
   * @param value value
   * @return true exists false does not exist
   */
  public boolean sHasKey(String key, Object value) {
    try {
      return redisTemplate.opsForSet().isMember(key, value);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }

  /**
   * Put the data into the set cache
   *
   * @param key key
   * @param values value can be multiple
   * @return number of successes
   */
  public long sSet(String key, Object... values) {
    try {
      return redisTemplate.opsForSet().add(key, values);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return 0;
    }
  }
  /**
   * Put the set data into the cache
   *
   * @param key key
   * @param time time (seconds)
   * @param values value can be multiple
   * @return number of successes
   */
  public long sSetAndTime(String key, long time, Object... values) {
    try {
      Long count = redisTemplate.opsForSet().add(key, values);
      if (time > 0) {
        expire(key, time);
      }

      return count;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return 0;
    }
  }

  /**
   * Get the length of the set cache
   *
   * @param key key
   * @return
   */
  public long sGetSetSize(String key) {
    try {
      return redisTemplate.opsForSet().size(key);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return 0;
    }
  }
  /**
   * Remove the value of value
   *
   * @param key key
   * @param values value can be multiple
   * @return the number of removed
   */
  public long setRemove(String key, Object... values) {
    try {
      Long count = redisTemplate.opsForSet().remove(key, values);
      return count;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return 0;
    }
  }

  // ===============================list=================================

  /**
   * Get the content of the list cache
   *
   * @param key key
   * @param start start
   * @param end end 0 to -1 represent all values
   * @return
   */
  public List<Object> lGet(String key, long start, long end) {
    try {
      return redisTemplate.opsForList().range(key, start, end);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }

  /**
   * Get the length of the list cache
   *
   * @param key key
   * @return
   */
  public long lGetListSize(String key) {
    try {
      return redisTemplate.opsForList().size(key);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return 0;
    }
  }

  /**
   * Get the value in the list by index
   *
   * @param key key
   * @param index index index>=0, 0 is the header, 1 is the second element, and so on; when index<0, -1 is the end of the table, -2 is the second-to-last element, and so on
   * @return
   */
  public Object lGetIndex(String key, long index) {
    try {
      return redisTemplate.opsForList().index(key, index);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }

  /**
   * Put the list into the cache
   *
   * @param key key
   * @param value value
   * @return
   */
  public boolean lSet(String key, Object value) {
    try {
      redisTemplate.opsForList().rightPush(key, value);
      return true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }
  /**
   * Put the list into the cache
   *
   * @param key key
   * @param value value
   * @param time time (seconds)
   * @return
   */
  public boolean lSet(String key, Object value, long time) {
    try {
      redisTemplate.opsForList().rightPush(key, value);
      if (time > 0) {
        expire(key, time);
      }

      return true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }

  /**
   * Put the list into the cache
   *
   * @param key key
   * @param value value
   * @return
   */
  public boolean lSet(String key, List<Object> value) {
    try {
      redisTemplate.opsForList().rightPushAll(key, value);
      return true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }

  /**
   * Put the list into the cache
   *
   * @param key key
   * @param value value
   * @param time time (seconds)
   * @return
   */
  public boolean lSet(String key, List<Object> value, long time) {
    try {
      redisTemplate.opsForList().rightPushAll(key, value);
      if (time > 0) {
        expire(key, time);
      }

      return true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }

  /**
   * Modify a piece of data in the list according to the index
   *
   * @param key key
   * @param index index
   * @param value value
   * @return /
   */
  public boolean lUpdateIndex(String key, long index, Object value) {
    try {
      redisTemplate.opsForList().set(key, index, value);
      return true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }

  /**
   * Remove N values as value
   *
   * @param key key
   * @param count how many to remove
   * @param value value
   * @return the number of removed
   */
  public long lRemove(String key, long count, Object value) {
    try {
      return redisTemplate.opsForList().remove(key, count, value);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return 0;
    }
  }

  /**
   * @param prefix prefix
   * @param ids id
   */
  public void delByKeys(String prefix, Set<Long> ids) {
    Set<Object> keys = new HashSet<>();
    for (Long id : ids) {
      keys.addAll(redisTemplate.keys(new StringBuffer(prefix).append(id).toString()));
    }

    long count = redisTemplate.delete(keys);

    // 此处提示可自行删除
    log.info("--------------------------------------------");
    log.info("Successfully deleted cache：" + keys.toString());
    log.info("Number of cache deletes：" + count );
    log.info("--------------------------------------------");
  }
}
