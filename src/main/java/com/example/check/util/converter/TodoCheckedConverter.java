package com.example.check.util.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// @Converter(autoApply = true) - Global 설정.
@Converter
public class TodoCheckedConverter implements AttributeConverter<Boolean, String> {

    public static final String CHECKED_ON  = "Y";
    public static final String CHECKED_OFF = "N";

    // Entity의 값을 받아서 DB로 전달할때 저장할 값 지정
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? CHECKED_ON : CHECKED_OFF;
    }

    // DB로 부터 조회시 변환값.
    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return CHECKED_ON.equals(dbData);
    }
}
