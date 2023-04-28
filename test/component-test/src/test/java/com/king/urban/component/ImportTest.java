package com.king.urban.component;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ArrayUtil;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.FeatureIterator;
import org.junit.Test;
import org.opengis.feature.simple.SimpleFeature;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class ImportTest {


    @Test
    public void importTest() throws IOException {
        File file = new File("/Users/liukai/Downloads/南岔数据库/部件/YT_0103_雨水井盖.shp");
        ShapefileDataStore fileDataStore = new ShapefileDataStore(file.toURI().toURL());
        fileDataStore.setCharset(Charset.forName("GBK"));
        String[] typeNames = fileDataStore.getTypeNames();
        if (ArrayUtil.isNotEmpty(typeNames)) {
            String typeName = typeNames[0];
            SimpleFeatureSource simpleFeatureSource = fileDataStore.getFeatureSource(typeName);

            SimpleFeatureCollection features = simpleFeatureSource.getFeatures();

            try (FeatureIterator<SimpleFeature> simpleFeatureIterator = features.features()) {
                while (simpleFeatureIterator.hasNext()) {
                    SimpleFeature simpleFeature = simpleFeatureIterator.next();
                    Object ObjID = simpleFeature.getAttribute("ObjID");
                    Object objName = simpleFeature.getAttribute("ObjName");
                    Object dataSource = simpleFeature.getAttribute("DataSource");
                    Object form = simpleFeature.getAttribute("Form");
                    Object picAddress = simpleFeature.getAttribute("PicAddress");
                    Object objState = simpleFeature.getAttribute("ObjState");
                    Object defaultGeometry = simpleFeature.getDefaultGeometry();

                    Console.log("{} {} {} {} {} {} {}", ObjID, objName, dataSource, form, picAddress, objState, defaultGeometry);

                }
            }


        }

    }

}
