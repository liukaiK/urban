package com.king.urban.main.component.controller;

import com.king.urban.common.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部件
 *
 * @author liukai
 */
@RestController
@RequestMapping("/component")
public class ComponentController {

    /**
     * 分页查询
     */
    @GetMapping("/search")
    public Result search(@PageableDefault Pageable pageable) {
        return Result.success();
    }

}
