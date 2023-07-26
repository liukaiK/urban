package com.king.urban.event.repository.code;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.StrUtil;
import com.king.urban.common.constant.RedisKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class EventCodeRepositoryImpl implements EventCodeRepository {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final static int EVENT_CODE_PREFIX_LENGTH = 8;

    private final static int EVENT_CODE_SUFFIX_LENGTH = 8;

    @Override
    public String generateNextCode() {
        String eventCode = redisTemplate.opsForValue().get(RedisKeys.EVENT_CODE);
        if (StrUtil.isEmpty(eventCode)) {
            return initEventCode();
        }
        if (eventCode.startsWith(getEventCodePrefixFromToday())) {
            return String.valueOf(redisTemplate.opsForValue().increment(RedisKeys.EVENT_CODE, 1));
        } else {
            return initEventCode();
        }
    }

    private String initEventCode() {
        String eventCodePrefix = getEventCodePrefixFromToday();
        String code = eventCodePrefix + StrUtil.fillBefore("1", '0', EVENT_CODE_SUFFIX_LENGTH);
        redisTemplate.opsForValue().set(RedisKeys.EVENT_CODE, code);
        return code;
    }

    private static String getEventCodePrefixFromToday() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(DatePattern.PURE_DATE_PATTERN));
    }

}
