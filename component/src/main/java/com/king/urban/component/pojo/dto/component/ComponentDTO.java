package com.king.urban.component.pojo.dto.component;

import lombok.Data;
import org.locationtech.jts.geom.Point;

@Data
public class ComponentDTO {

    /**
     * 部件编码
     */
    private String code;

    private String name;

    private String source;

    /**
     * 主管部门编码
     */
    private String mainDeptCode;

    /**
     * 主管部门名称
     */
    private String mainDeptName;

    /**
     * 权属部门名称
     */
    private String ownerDeptName;

    /**
     * 权属部门编码
     */
    private String ownerDeptCode;

    /**
     * 养护部门名称
     */
    private String keepDeptName;

    /**
     * 养护部门编码
     */
    private String keepDeptCode;

    /**
     * 形状
     */
    private String form;

    /**
     * 部件地址
     */
    private String locate;

    /**
     * 部件图片
     */
    private String picUrl;

    /**
     * 坐标
     */
    private Point point;

    /**
     * 状态
     */
    private String state;

    /**
     * 材料
     */
    private String material;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

}
