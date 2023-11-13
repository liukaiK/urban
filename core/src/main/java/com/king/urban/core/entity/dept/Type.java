package com.king.urban.core.entity.dept;

import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.Map;

public enum Type {

    CENTRAL_DEPT(1, "中心部门"),
    PROFESSION_DEPT(2, "专业部门");

    Type(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<Integer, Type> map = new HashMap<>(6);

    private final Integer key;

    private final String value;

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    static {
        for (Type type : values()) {
            map.put(type.getKey(), type);
        }
    }

    public static Type get(Integer key) {
        Type type = map.get(key);
        if (type == null) {
            throw new IllegalArgumentException("type key cannot be null");
        }
        return type;
    }

    public static class Converter implements AttributeConverter<Type, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Type type) {
            if (type == null) {
                throw new IllegalArgumentException("Unknown type text  ");
            }
            return type.getKey();

        }

        @Override
        public Type convertToEntityAttribute(Integer key) {
            return Type.get(key);
        }

    }

}