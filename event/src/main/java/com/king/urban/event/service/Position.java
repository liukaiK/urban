package com.king.urban.event.service;

/**
 * 定位信息
 *
 * @author liukai
 */
public interface Position extends Report {

    /**
     * 获取经度
     */
    String getLongitude();

    /**
     * 获取纬度
     */
    String getLatitude();

    /**
     * 获取位置信息
     */
    String getAddress();


}
