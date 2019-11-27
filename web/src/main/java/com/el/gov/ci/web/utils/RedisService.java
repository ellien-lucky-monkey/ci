package com.el.gov.ci.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.util.concurrent.TimeUnit;

/**
 * @author Jiangkui
 * @since 2019/11/01 09:32
 */
@Component
public class RedisService {

    private static final Logger log = LoggerFactory.getLogger(RedisService.class);
    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean lock(@NotEmpty String key, Long expire) {
        boolean lock = false;
        try {
            lock = redisTemplate.opsForValue().setIfAbsent(key, "LOCK");
            if (lock) {
                redisTemplate.expire(key, expire, TimeUnit.SECONDS);
            }
        } finally {
            if (lock) {
                redisTemplate.delete(key);
            } else {
                log.info("锁已经释放");
            }
        }
        return lock;
    }

    public boolean unlock(@NotEmpty String key) {
        String s = redisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(s)) {
            return true;
        }
        redisTemplate.delete(key);
        return true;
    }
}
