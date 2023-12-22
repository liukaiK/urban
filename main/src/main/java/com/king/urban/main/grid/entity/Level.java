package com.king.urban.main.grid.entity;


import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.Map;

public enum Level {

    CITY(0, "市"),
    REGION(1, "区县"),
    STREET(2, "街道"),
    COMMUNITY(3, "社区"),
    GRID(4, "万米单元网格");

    Level(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<Integer, Level> map = new HashMap<>(6);

    private final Integer key;

    private final String value;

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    static {
        for (Level level : values()) {
            map.put(level.getKey(), level);
        }
    }

    public static Level get(Integer key) {
        Level level = map.get(key);
        if (level == null) {
            throw new IllegalArgumentException("level key cannot be null");
        }
        return level;
    }


    public static class Converter implements AttributeConverter<Level, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Level level) {
            if (level == null) {
                throw new IllegalArgumentException("Unknown level text  ");
            }
            return level.getKey();

        }

        @Override
        public Level convertToEntityAttribute(Integer key) {
            return Level.get(key);
        }


    }

}
