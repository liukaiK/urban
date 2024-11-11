package com.king.urban.test.event.repository.code;

import cn.hutool.core.lang.Assert;
import com.king.urban.event.repository.code.EventCodeRepository;
import com.king.urban.test.event.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EventCodeRepositoryTest extends BaseTest {

    @Autowired
    private EventCodeRepository eventCodeRepository;

    @Test
    public void EventCodeGenerateTest() {
        String eventCode = eventCodeRepository.generateNextCode();

        Assert.notEmpty(eventCode);

    }

}
