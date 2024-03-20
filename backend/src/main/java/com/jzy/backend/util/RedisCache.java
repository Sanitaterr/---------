package com.jzy.backend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/20 20:09
 * @Description: TODO
 */
@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class RedisCache {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     *
     * 缓存基本的对象，Integer、String、实体类等
     * @param key 缓存的键值
     * @param value 缓存的值
     * @author jzy
     * @create 2024/3/20
     **/
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     * @author jzy
     * @create 2024/3/20
     **/
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     *
     * 设置有效时间
     * @param key Redis键
     * @param timeout 超时时间
     * @return true = 设置成功, false = 设置失败
     * @author jzy
     * @create 2024/3/20
     **/
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return true = 设置成功, false = 设置失败
     * @author jzy
     * @create 2024/3/20
     **/
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     *
     * 获得缓存的基本对象
     * @param key 缓存键值
     * @return T
     * @author jzy
     * @create 2024/3/20
     **/
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     *
     * 删除单个对象
     * @param key
     * @author jzy
     * @create 2024/3/20
     **/
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     *
     * 删除集合对象
     * @param collection 多个对象
     * @return
     * @author jzy
     * @create 2024/3/20
     **/
    public long deleteaObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     *
     * 缓存List数据
     * @param key 缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     * @author jzy
     * @create 2024/3/20
     **/
    public <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     *
     * 获得缓存的list对象
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     * @author jzy
     * @create 2024/3/20
     **/
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     *
     * 缓存set
     * @param key 缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     * @author jzy
     * @create 2024/3/20
     **/
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperations = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperations.add(it.next());
        }
        return setOperations;
    }

    /**
     *
     * 获得缓存的set
     * @param key
     * @return
     * @author jzy
     * @create 2024/3/20
     **/
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     *
     * 缓存Map
     * @param key
     * @param dataMap
     * @author jzy
     * @create 2024/3/20
     **/
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     *
     * 获得缓存的Map
     * @param key
     * @return
     * @author jzy
     * @create 2024/3/20
     **/
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     *
     * 往Hash中存入数据
     * @param key Redis键
     * @param hKey Hash键
     * @param value 值
     * @author jzy
     * @create 2024/3/20
     **/
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     *
     * 获取Hash中的数据
     * @param key Redis键
     * @param hKey Hash键
     * @return T
     * @author jzy
     * @create 2024/3/20
     **/
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     *
     * 删除Hash中的数据
     * @param key
     * @param hKey
     * @author jzy
     * @create 2024/3/20
     **/
    public void delCacheMapValue(final String key, final String hKey) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, hKey);
    }

    /**
     *
     * 获取多个Hash中的数据
     * @param key Redis键
     * @param hKeys Hash键
     * @return Hash对象集合
     * @author jzy
     * @create 2024/3/20
     **/
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     *
     * 获得缓存的基本对象列表
     * @param pattern 字符串前缀
     * @return 对象列表
     * @author jzy
     * @create 2024/3/20
     **/
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }
}
