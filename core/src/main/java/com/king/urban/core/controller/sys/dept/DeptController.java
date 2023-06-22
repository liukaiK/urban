package com.king.urban.core.controller.sys.dept;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.king.urban.common.Result;
import com.king.urban.core.pojo.dto.dept.CreateDeptDTO;
import com.king.urban.core.pojo.dto.dept.RemoveDeptDTO;
import com.king.urban.core.pojo.dto.dept.SearchDeptDTO;
import com.king.urban.core.pojo.vo.dept.DeptVO;
import com.king.urban.core.service.dept.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * 部门控制器
 *
 * @author liukai
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/search")
    @SaCheckPermission("system:dept:search")
    public Result search(SearchDeptDTO searchDeptDTO, @PageableDefault Pageable pageable) {
        Page<DeptVO> page = deptService.search(searchDeptDTO, pageable);
        return Result.success(page);
    }

    @PostMapping("/create")
    public Result create(@RequestBody CreateDeptDTO createDeptDTO) {
        deptService.create(createDeptDTO);
        return Result.success();
    }


    @PostMapping("/remove")
    public Result remove(@RequestBody RemoveDeptDTO removeDeptDTO) {
        deptService.remove(removeDeptDTO);
        return Result.success();
    }


}
