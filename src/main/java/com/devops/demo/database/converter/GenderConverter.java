package com.devops.demo.database.converter;

import com.devops.demo.database.enums.Gender;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        return (gender == null) ? null : gender.getValue();
    }

    @Override
    public Gender convertToEntityAttribute(Integer dbData) {
        return (dbData == null) ? null : Gender.fromValue(dbData);
    }
}
