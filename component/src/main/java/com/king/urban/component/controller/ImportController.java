package com.king.urban.component.controller;

import cn.hutool.core.io.FileUtil;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 部件导入
 *
 * @author liukai
 */
@RestController
@RequestMapping("/component")
public class ImportController {

    private static final Logger log = LoggerFactory.getLogger(ImportController.class);

    /**
     * 导入shp
     */
    @PostMapping("/import")
    public void importShp(@RequestPart MultipartFile file) throws IOException {
        String tmpDirPath = FileUtil.getTmpDirPath();
        String path = tmpDirPath + file.getOriginalFilename();
        File dest = new File(path);
        file.transferTo(dest);

        DataStore dataStore = DataStoreFinder.getDataStore(null);

        String typeName = dataStore.getTypeNames()[0];

        SimpleFeatureSource simpleFeatureSource = dataStore.getFeatureSource(typeName);


        SimpleFeatureCollection features = simpleFeatureSource.getFeatures();

        try (SimpleFeatureIterator features1 = features.features()) {

        }


    }

}
