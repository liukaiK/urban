package com.king.urban.workflow.complete;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompleteServiceImpl implements CompleteService {

//    @Autowired
//    private TaskService taskService;

    @Override
    public void finish(String taskId, String buttonId, String comment) {

    }

    @Override
    public void complete(String eventId, List<String> userList, String taskId, String buttonId, String comment) {
//        taskService.complete(taskId);
    }


}
