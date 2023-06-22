package com.king.urban.common.jpa;

import com.king.urban.common.util.AESUtil;
import com.king.urban.common.util.StringUtils;

import javax.persistence.AttributeConverter;

public class MobilePhoneConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (StringUtils.hasText(attribute)) {
            return AESUtil.encrypt(attribute);
        }
        return null;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (StringUtils.hasText(dbData)) {
            return AESUtil.decrypt(dbData);
        }
        return null;
    }

}
