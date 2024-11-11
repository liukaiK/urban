package com.king.urban.event.entity.event;


import jakarta.persistence.AttributeConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * 案件来源
 *
 * @author liukai
 */
public enum Source {

    GRID_ADMIN(0, "网格员上报"),
    TELEPHONE(1, "热线上报"),
    PUBLIC(2, "公众上报"),
    SMALL(3, "微采集"),
    VIDEO_ALARM(4, "视频智能识别");

    Source(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<Integer, Source> map = new HashMap<>(6);

    private final Integer key;
    private final String value;

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static Source get(Integer key) {
        Source source = map.get(key);
        if (source == null) {
            throw new IllegalArgumentException("source key cannot be null");
        }
        return source;
    }

    static {
        for (Source source : values()) {
            map.put(source.getKey(), source);
        }
    }


    public static class Converter implements AttributeConverter<Source, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Source source) {
            if (source == null) {
                throw new IllegalArgumentException("Unknown source text  ");
            }
            return source.getKey();

        }

        @Override
        public Source convertToEntityAttribute(Integer key) {
            return Source.get(key);
        }


    }

}
