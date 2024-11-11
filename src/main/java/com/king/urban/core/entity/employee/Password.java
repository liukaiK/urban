package com.king.urban.core.entity.employee;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author liukai
 */
@Embeddable
public class Password {

    @Convert(converter = PasswordConverter.class)
    private String password;

    protected Password() {

    }

    public Password(String password) {
        this.setPassword(password);
    }


    public String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    /**
     * 密码加密
     */
    static class PasswordConverter implements AttributeConverter<String, String> {

        private final static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        @Override
        public String convertToDatabaseColumn(String attribute) {
            return passwordEncoder.encode(attribute);
        }

        @Override
        public String convertToEntityAttribute(String dbData) {
            return dbData;
        }

    }

}
