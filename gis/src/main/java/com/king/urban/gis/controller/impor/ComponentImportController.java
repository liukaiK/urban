package com.king.urban.gis.controller.impor;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import com.king.urban.common.Result;
import com.king.urban.component.pojo.dto.component.ComponentDTO;
import com.king.urban.component.service.component.ComponentService;
import com.king.urban.gis.entity.mapping.Type;
import com.king.urban.gis.service.MappingService;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.FeatureIterator;
import org.locationtech.jts.geom.Point;
import org.opengis.feature.simple.SimpleFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 部件导入
 *
 * @author liukai
 */
@RestController
@RequestMapping("/component")
public class ComponentImportController {

    private static final Logger log = LoggerFactory.getLogger(ComponentImportController.class);

    @Autowired
    private MappingService mappingService;

    @Autowired
    private ComponentService componentService;

    /**
     * 把部件的shp数据导入到数据库里面
     */
    @PostMapping("/import")
    public Result importShp(String charset, @RequestPart MultipartFile[] files) throws IOException {

        List<File> shpFiles = new ArrayList<>();

        for (MultipartFile multipartFile : files) {
            File newFile = new File(FileUtil.getTmpDirPath() + multipartFile.getOriginalFilename());
            multipartFile.transferTo(newFile);

            if ("shp".equals(FileUtil.getSuffix(newFile))) {
                shpFiles.add(newFile);
            }
        }
        for (File shpFile : shpFiles) {
            saveToDatabase(charset, shpFile);
        }
        return Result.success();
    }

    private void saveToDatabase(String charset, File shpFile) throws IOException {
        ShapefileDataStore fileDataStore = new ShapefileDataStore(shpFile.toURI().toURL());
        fileDataStore.setCharset(Charset.forName(charset));
        String[] typeNames = fileDataStore.getTypeNames();

        if (ArrayUtil.isNotEmpty(typeNames)) {
            List<ComponentDTO> componentDTOs = new ArrayList<>();
            Map<String, String> mapping = mappingService.findAll(Type.COMPONENT);
            try (FeatureIterator<SimpleFeature> simpleFeatureIterator = getFeatures(fileDataStore, typeNames)) {
                while (simpleFeatureIterator.hasNext()) {
                    SimpleFeature simpleFeature = simpleFeatureIterator.next();
                    if (simpleFeature.getDefaultGeometryProperty().getValue() instanceof Point) {
                        ComponentDTO componentDTO = buildComponentDTO(mapping, simpleFeature);
                        componentDTOs.add(componentDTO);
                    } else {
                        log.warn("{} 不是点数据 无法导入到数据库中", typeNames);
                    }
                }
            }
            componentService.create(componentDTOs);
        }
    }


    private static SimpleFeatureIterator getFeatures(ShapefileDataStore fileDataStore, String[] typeNames) throws IOException {
        return ((SimpleFeatureSource) fileDataStore.getFeatureSource(typeNames[0])).getFeatures().features();
    }

    private static ComponentDTO buildComponentDTO(Map<String, String> mapping, SimpleFeature simpleFeature) {
        String code = (String) simpleFeature.getAttribute(mapping.get("code"));
        String name = (String) simpleFeature.getAttribute(mapping.get("name"));
        String source = (String) simpleFeature.getAttribute(mapping.get("source"));
        String picUrl = (String) simpleFeature.getAttribute(mapping.get("pic_url"));
        String form = (String) simpleFeature.getAttribute(mapping.get("form"));
        String locate = (String) simpleFeature.getAttribute(mapping.get("locate"));
        String keepDeptCode = (String) simpleFeature.getAttribute(mapping.get("keep_dept_code"));
        String keepDeptName = (String) simpleFeature.getAttribute(mapping.get("keep_dept_name"));

        String mainDeptCode = (String) simpleFeature.getAttribute(mapping.get("main_dept_code"));
        String mainDeptName = (String) simpleFeature.getAttribute(mapping.get("main_dept_name"));

        String ownerDeptCode = (String) simpleFeature.getAttribute(mapping.get("owner_dept_code"));
        String ownerDeptName = (String) simpleFeature.getAttribute(mapping.get("owner_dept_name"));

        String material = (String) simpleFeature.getAttribute(mapping.get("material"));
        String state = (String) simpleFeature.getAttribute(mapping.get("state"));

        Point point = (Point) simpleFeature.getDefaultGeometry();

        ComponentDTO componentDTO = new ComponentDTO();
        componentDTO.setCode(code);
        componentDTO.setName(name);
        componentDTO.setSource(source);
        componentDTO.setPicUrl(picUrl);
        componentDTO.setForm(form);
        componentDTO.setLocate(locate);
        componentDTO.setKeepDeptCode(keepDeptCode);
        componentDTO.setKeepDeptName(keepDeptName);

        componentDTO.setMainDeptCode(mainDeptCode);
        componentDTO.setMainDeptName(mainDeptName);

        componentDTO.setOwnerDeptCode(ownerDeptCode);
        componentDTO.setOwnerDeptName(ownerDeptName);

        componentDTO.setState(state);
        componentDTO.setMaterial(material);
        componentDTO.setPoint(point);

        return componentDTO;
    }

}
