package com.king.urban.core.entity.employee;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;

@Embeddable
public class Name {

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
