package com.itechart.mapper;

import com.itechart.entity.EmployeeEntity;
import com.itechart.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper EMPLOYEE_MAPPER = Mappers.getMapper(EmployeeMapper.class);

    Employee toModel(EmployeeEntity userEntity);

}
