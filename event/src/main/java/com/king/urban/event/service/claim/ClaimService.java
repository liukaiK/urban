package com.king.urban.event.service.claim;


import com.king.urban.core.entity.employee.Employee;
import com.king.urban.event.entity.Event;

public interface ClaimService {

    void claim(Event event, Employee employee);

}
