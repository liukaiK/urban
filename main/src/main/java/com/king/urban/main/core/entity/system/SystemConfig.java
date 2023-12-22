package com.king.urban.main.core.entity.system;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 系统配置
 *
 * @author liukai
 */
@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
@Table(name = "t_sys_config")
public class SystemConfig extends DeletableEntity<Long> {

    private String name;

    private String value;

    private String description;


}
