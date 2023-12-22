package com.king.urban.main.gis.impor;

import com.king.urban.common.Result;
import com.king.urban.main.gis.service.MappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/cellgrid")
public class CellGridImportController {

    private static final Logger log = LoggerFactory.getLogger(CellGridImportController.class);


    @Autowired
    private MappingService mappingService;


    @PostMapping("/import")
    public Result importShp(String charset, @RequestPart MultipartFile[] files) throws IOException {



        return Result.success();
    }


}
