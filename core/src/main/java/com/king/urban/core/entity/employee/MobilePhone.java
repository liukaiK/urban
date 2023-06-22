package com.king.urban.core.entity.employee;

import com.king.urban.common.jpa.MobilePhoneConverter;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
public class MobilePhone {

    @Convert(converter = MobilePhoneConverter.class)
    private String mobilePhone;

    protected MobilePhone() {
    }

    public MobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

}
