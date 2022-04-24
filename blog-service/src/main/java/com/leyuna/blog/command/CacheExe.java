package com.leyuna.blog.command;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author pengli
 * @create 2021-09-02 15:24
 * <p>
 * 清除缓存指令
 */
@Service
public class CacheExe {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //存放需要消除的缓存key
    private CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList();
    /**
     * 私有化构造，准备单例
     */

    /**
     * 清空当前所有缓存
     */
    public void clearAllCache (String key) {
        Set<String> allKeys = getAllKeys();
        for(String redis:allKeys){
            if(redis.contains(key)){
                stringRedisTemplate.delete(redis);
            }
        }
    }

    public void clearAllCache () {
        Set<String> allKeys = getAllKeys();
        if (ObjectUtils.isNotEmpty(allKeys)) {
            stringRedisTemplate.delete(allKeys);
        }
    }

    /**
     * 获得所有key
     * @return
     */
    public Set<String> getAllKeys () {
        Set<String> keys = stringRedisTemplate.keys("*");
        return keys;
    }

    /**
     * 判断redis是否存在该key
     * @param key
     * @return
     */
    public boolean hasCacheByKey (String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 设置key - value
     * @param key
     * @param value
     * @param sec
     */
    public void setCacheKey (String key, String value, long sec) {
        stringRedisTemplate.opsForValue().set(key, value, sec * 1, TimeUnit.SECONDS);
    }

    /**
     * key获取value
     * @param key
     * @return
     */
    public String getCacheByKey (String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void clearCacheKey (String key) {
        stringRedisTemplate.delete(key);
    }
}
