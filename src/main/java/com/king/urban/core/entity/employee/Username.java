package com.king.urban.core.entity.employee;


import com.king.urban.common.util.StringUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


/**
 * @author liukai
 */
@Embeddable
public class Username {

    @Column(length = 50, nullable = false)
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
        if (StringUtils.hasText(username)) {
            this.username = username;
            return;
        }
        throw new IllegalArgumentException("username cannot be null");

    }

    @Override
    public String toString() {
        return this.username;
    }
}
