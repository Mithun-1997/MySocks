package com.devops.demo.database.converter;

import com.devops.demo.database.enums.Role;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Role role) {
        return (role == null) ? null : role.getValue();
    }

    @Override
    public Role convertToEntityAttribute(Integer dbData) {
        return (dbData == null) ? null : Role.fromValue(dbData);
    }
}
