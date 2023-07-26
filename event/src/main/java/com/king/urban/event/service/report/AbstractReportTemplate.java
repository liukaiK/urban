package com.king.urban.event.service.report;

import cn.hutool.core.collection.CollectionUtil;
import com.king.urban.event.entity.event.Event;
import com.king.urban.event.entity.event.Position;
import com.king.urban.event.entity.event.Source;
import com.king.urban.event.entity.event.Workflow;
import com.king.urban.event.pojo.report.ReportDTO;
import com.king.urban.event.repository.EventRepository;
import com.king.urban.event.repository.code.EventCodeRepository;
import com.king.urban.workflow.process.WorkflowProcessInstanceService;
import com.king.urban.workflow.task.WorkflowTaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractReportTemplate {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCodeRepository eventCodeRepository;

    @Autowired
    private WorkflowProcessInstanceService workflowProcessInstanceService;

    @Autowired
    private WorkflowTaskService workflowTaskService;


    public final void report(ReportDTO reportDTO) {
        Source source = getSource();

        if (source == null) {
            throw new IllegalArgumentException("source cannot be null");
        }


        Event event = new Event(source);
        event.updatePosition(getPosition(reportDTO));
        event.updateCode(eventCodeRepository.generateNextCode());


        eventRepository.save(event);

        Workflow workflow = startProcess(event);

        event.updateWorkflow(workflow);


    }

    private Position getPosition(ReportDTO reportDTO) {
        return new Position(Double.valueOf(reportDTO.getLongitude()), Double.valueOf(reportDTO.getLatitude()), reportDTO.getAddress());
    }


    protected abstract Source getSource();

    private Workflow startProcess(Event event) {
        String processInstanceId = workflowProcessInstanceService.startProcessInstanceByKey("", String.valueOf(event.getId()));
        List<Task> tasks = workflowTaskService.queryTask(processInstanceId);
        if (CollectionUtil.isNotEmpty(tasks) && tasks.size() >= 2) {
            throw new IllegalArgumentException("不对啊 怎么两个task呢");
        }
        return new Workflow(tasks.get(0).getId(), tasks.get(0).getName(), processInstanceId);
    }

}
