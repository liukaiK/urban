package com.king.urban.event.service;

public interface BasicReport extends Position {

    /**
     * 获取案件分类
     */
    String getEventTypeId();

    /**
     * 获取责任网格ID
     */
    String getDutyGridId();

    /**
     * 获取单元网格ID
     */
    String getCellGridId();

}
