/**
 * Copyright (c) 2023,人民邮电出版社,《深入Activiti流程引擎：核心原理与高阶实战》
 * All rights reserved.
 * 本案例代码节选自于贺波、胡海琴和刘晓鹏三位老师的著作《深入Activiti流程引擎：核心原理与高阶实战》。
 * 若需获取本书的完整案例及代码，请访问人民邮电出版社该书首页进行下载，网址为：https://www.epubit.com/bookDetails?id=UBd189db7e65bd。
 */

package com.king.urban.main.workflow;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.api.FlowableIllegalArgumentException;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.impl.context.Context;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityManager;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.impl.util.ProcessDefinitionUtil;

import java.util.List;

/**
 * @author liukai
 */
@AllArgsConstructor
public class DynamicJumpCmd implements Command<Void> {

    // 流程实例编号
    protected String processInstanceId;
    // 跳转起始节点
    protected String fromActivityId;
    // 跳转目标节点
    protected String toActivityId;

    public Void execute(CommandContext commandContext) {
        //processInstanceId参数不能为空
        if (this.processInstanceId == null) {
            throw new FlowableIllegalArgumentException("Process instance id is required");
        }
        //获取执行实例管理类
        ExecutionEntityManager executionEntityManager = CommandContextUtil.getExecutionEntityManager();
        //获取执行实例
        ExecutionEntity execution = executionEntityManager.findById(this.processInstanceId);
        if (execution == null) {
            throw new FlowableException("Execution could not be found with id " + this.processInstanceId);
        }
        if (!execution.isProcessInstanceType()) {
            throw new FlowableException("Execution is not a process instance type execution for id " + this.processInstanceId);
        }
        ExecutionEntity activeExecutionEntity = null;
        //获取所有子执行实例
        List<ExecutionEntity> childExecutions = executionEntityManager.findChildExecutionsByProcessInstanceId(execution.getId());
        for (ExecutionEntity childExecution : childExecutions) {
            if (childExecution.getCurrentActivityId().equals(this.fromActivityId)) {
                activeExecutionEntity = childExecution;
            }
        }
        if (activeExecutionEntity == null) {
            throw new FlowableException("Active execution could not be found with activity id " + this.fromActivityId);
        }
        // 获取流程模型
        BpmnModel bpmnModel = ProcessDefinitionUtil.getBpmnModel(execution.getProcessDefinitionId());
        //获取当前节点
        FlowElement fromActivityElement = bpmnModel.getFlowElement(this.fromActivityId);
        // 获取目标节点
        FlowElement toActivityElement = bpmnModel.getFlowElement(this.toActivityId);
        // 校验id为fromActivityId的节点是否存在
        if (fromActivityElement == null) {
            throw new FlowableException("Activity could not be found in process definition for id " + this.fromActivityId);
        }
        // 校验id为toActivityId的节点是否存在
        if (toActivityElement == null) {
            throw new FlowableException("Activity could not be found in process definition for id " + this.toActivityId);
        }
        boolean deleteParentExecution = false;
        ExecutionEntity parentExecution = activeExecutionEntity.getParent();
        // 兼容子流程节点的场景
        if ((fromActivityElement.getSubProcess() != null) && ((toActivityElement.getSubProcess() == null) || (!toActivityElement.getSubProcess().getId().equals(parentExecution.getActivityId())))) {
            deleteParentExecution = true;
        }
        // 删除当前节点所在的执行实例及相关数据
        executionEntityManager.deleteExecutionAndRelatedData(activeExecutionEntity, StrUtil.format("Change activity to {}", this.toActivityId), false);
        // 如果是子流程节点，删除其所在的执行实例及相关数据
        if (deleteParentExecution) {
            executionEntityManager.deleteExecutionAndRelatedData(parentExecution, StrUtil.format("Change activity to {}", this.toActivityId), false);
        }
        // 创建当前流程实例的子执行实例
        ExecutionEntity newChildExecution = executionEntityManager.createChildExecution(execution);
        // 设置执行实例的当前活动节点为目标节点
        newChildExecution.setCurrentFlowElement(toActivityElement);
        // 向operations中压入继续流程的操作类
        Context.getAgenda().planContinueProcessOperation(newChildExecution);

        return null;
    }
}