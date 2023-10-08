package com.king.urban.core.entity.employee;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Name {

    @NotNull
    @Size(max = 10, message = "姓名长度不能超过10")
    private String name;

    protected Name() {

    }

    public Name(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("name cannot be null");
        }
        this.name = name;
    }


}
