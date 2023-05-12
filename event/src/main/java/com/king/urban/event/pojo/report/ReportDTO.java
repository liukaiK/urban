package com.king.urban.event.pojo.report;

public interface ReportDTO {

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

    /**
     * 获取经度
     */
    String getLongitude();

    /**
     * 获取纬度
     */
    String getLatitude();

    String getAddress();

}
