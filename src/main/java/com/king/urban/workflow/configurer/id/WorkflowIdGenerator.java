package com.king.urban.workflow.configurer.id;

import cn.hutool.core.util.IdUtil;
import org.flowable.common.engine.impl.cfg.IdGenerator;
import org.springframework.stereotype.Component;

@Component
public class WorkflowIdGenerator implements IdGenerator {

    @Override
    public String getNextId() {
        return IdUtil.getSnowflake().nextIdStr();
    }

}
