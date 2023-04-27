package com.king.component.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 部件导入
 *
 * @author liukai
 */
@RestController
@RequestMapping("/component")
public class ImportController {


    /**
     * 导入shp
     */
    @PostMapping("/import")
    public void importShp(MultipartFile file) {

    }

}
