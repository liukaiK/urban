package com.king.urban.event;

import java.util.List;

public interface A {

    void addHandler(ReportHandler reportHandler);

    List<ReportHandler> get();

}
