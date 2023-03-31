package com.king.urban.workflow.complete;

import java.util.List;

public interface CompleteService {


    void finish(String taskId, String buttonId, String comment);


    void complete(String eventId, List<String> userList, String taskId, String buttonId, String comment);


}
