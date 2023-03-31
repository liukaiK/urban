package com.king.urban.core.entity.employee;


import org.springframework.util.StringUtils;

import javax.persistence.Embeddable;

/**
 * @author liukai
 */
@Embeddable
public class Username {

    private String username;


    protected Username() {

    }

    public Username(String username) {
        this.setUsername(username);
    }

    public String getUsername() {
        return username;
    }

    protected void setUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("username cannot be null");
        }
        this.username = username;
    }

}
