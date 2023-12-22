package com.king.urban.main.core.service.dept;

import com.king.urban.main.core.pojo.dto.dept.CreateDeptDTO;
import com.king.urban.main.core.pojo.dto.dept.RemoveDeptDTO;
import com.king.urban.main.core.pojo.dto.dept.SearchDeptDTO;
import com.king.urban.main.core.pojo.vo.dept.DeptVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DeptService {

    Page<DeptVO> search(SearchDeptDTO searchDeptDTO, Pageable pageable);

    void create(CreateDeptDTO createDeptDTOø);

    void remove(RemoveDeptDTO removeDeptDTO);

}
