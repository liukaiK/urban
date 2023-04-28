package com.king.urban.component.service.component;

import com.king.urban.component.pojo.dto.component.ComponentDTO;

import java.util.List;

public interface ComponentService {


    void create(ComponentDTO componentDTO);

    void create(List<ComponentDTO> componentDTOs);

//    void importShp(List<ComponentDTO> componentDTOs);


}
