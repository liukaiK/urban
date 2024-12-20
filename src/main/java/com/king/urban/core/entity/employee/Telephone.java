package com.king.urban.core.entity.employee;

import com.king.urban.common.jpa.TelephoneConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.Getter;


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
