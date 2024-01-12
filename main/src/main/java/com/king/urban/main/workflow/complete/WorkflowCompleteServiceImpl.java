package com.king.urban.main.workflow.complete;

import cn.hutool.core.util.StrUtil;
import com.king.urban.main.event.entity.event.Event;
import com.king.urban.main.event.repository.EventRepository;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkflowCompleteServiceImpl implements WorkflowCompleteService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void finish(String taskId, String buttonId, String comment) {

    }

    @Override
    public void complete(Long eventId, List<String> userList, String taskId, String buttonId, String comment) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException(StrUtil.format("eventId:{}不存在", eventId)));
        taskService.addComment(taskId, event.getWorkflow().getProcessInstanceId(), comment);
        taskService.complete(taskId);
    }


}
