package com.king.urban.component.component;

import com.king.urban.component.pojo.dto.component.ComponentDTO;
import com.king.urban.component.pojo.vo.ComponentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComponentService {


    Page<ComponentVO> search(Pageable pageable);

    void create(ComponentDTO componentDTO);

    void create(List<ComponentDTO> componentDTOs);

//    void importShp(List<ComponentDTO> componentDTOs);


}
