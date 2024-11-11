package com.king.urban.core.service.dept;

import com.king.urban.core.entity.dept.Dept;
import com.king.urban.core.pojo.dto.dept.CreateDeptDTO;
import com.king.urban.core.pojo.dto.dept.RemoveDeptDTO;
import com.king.urban.core.pojo.dto.dept.SearchDeptDTO;
import com.king.urban.core.pojo.vo.dept.DeptVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DeptService {

    Page<DeptVO> search(SearchDeptDTO searchDeptDTO, Pageable pageable);

    Dept create(CreateDeptDTO createDeptDTOø);

    void remove(RemoveDeptDTO removeDeptDTO);

}
