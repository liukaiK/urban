package com.king.urban.event.pojo.report;

import lombok.Data;

@Data
public class TelephoneReportDTO implements ReportDTO {

    private String longitude;

    private String latitude;

    @Override
    public String getEventTypeId() {
        return null;
    }

    @Override
    public String getDutyGridId() {
        return null;
    }

    @Override
    public String getCellGridId() {
        return null;
    }

    @Override
    public String getLongitude() {
        return longitude;
    }

    @Override
    public String getLatitude() {
        return latitude;
    }

    @Override
    public String getAddress() {
        return null;
    }

}
