package com.king.urban.gis.entity;

import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.Map;

public enum Type {

    Component(0, "部件");

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

    public static Type get(Integer key) {
        Type type = map.get(key);
        if (type == null) {
            throw new IllegalArgumentException("type key cannot be null");
        }
        return type;
    }

    static {
        for (Type type : values()) {
            map.put(type.getKey(), type);
        }
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
            return map.get(key);
        }
    }
}
