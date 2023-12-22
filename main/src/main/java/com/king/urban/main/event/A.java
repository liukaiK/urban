package com.king.urban.main.event;

import java.util.List;

public interface A {

    void addHandler(ReportHandler reportHandler);

    List<ReportHandler> get();

}
