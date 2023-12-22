package com.king.urban.main.event.service.claim;


import com.king.urban.main.core.entity.employee.Employee;
import com.king.urban.main.event.entity.event.Event;

public interface ClaimService {

    void claim(Event event, Employee employee);

}
