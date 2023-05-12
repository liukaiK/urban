package com.king.urban.event.repository.code;

import com.king.urban.common.constant.RedisKeys;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventCodeRepositoryImpl implements EventCodeRepository {

    private StringRedisTemplate redisTemplate;

    @Override
    public String generateNextCode() {
        redisTemplate.opsForValue().increment(RedisKeys.EVENT_CODE, 1);
        return null;
    }

}
