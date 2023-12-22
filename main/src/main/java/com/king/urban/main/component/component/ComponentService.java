package com.king.urban.main.component.component;

import com.king.urban.main.component.pojo.dto.component.ComponentDTO;
import com.king.urban.main.component.pojo.vo.ComponentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComponentService {


    Page<ComponentVO> search(Pageable pageable);

    void create(ComponentDTO componentDTO);

    void create(List<ComponentDTO> componentDTOs);

//    void importShp(List<ComponentDTO> componentDTOs);


}
