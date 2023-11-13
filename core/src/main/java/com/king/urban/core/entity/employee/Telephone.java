package com.king.urban.core.entity.employee;

import com.king.urban.common.jpa.TelephoneConverter;
import lombok.Getter;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

/**
 * 电话号码
 */
@Getter
@Embeddable
public class Telephone {

    @Convert(converter = TelephoneConverter.class)
    private String telephone;

    protected Telephone() {
    }

    public Telephone(String telephone) {
        this.telephone = telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
