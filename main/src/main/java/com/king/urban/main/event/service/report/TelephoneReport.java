package com.king.urban.main.event.service.report;

import com.king.urban.main.event.service.Report;
import lombok.Data;

@Data
public class TelephoneReport implements Report {

    private String eventTypeId;

    private String dutyGridId;

    private String cellGridId;

    private String longitude;

    private String latitude;

    private String address;


}
