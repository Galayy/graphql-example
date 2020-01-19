package com.itechart.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.itechart.entity.EmployeeEntity;
import com.itechart.model.Employee;
import com.itechart.model.EmployeeInput;
import com.itechart.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.itechart.mapper.EmployeeMapper.EMPLOYEE_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeMutations implements GraphQLMutationResolver {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public Employee newEmployee(EmployeeInput input) {
        var entity = EmployeeEntity.builder()
                .age(input.getAge())
                .name(input.getName())
                .position(input.getPosition())
                .salary(input.getSalary())
                .build();
        employeeRepository.save(entity);
        log.info("Employee successfully saved");
        return EMPLOYEE_MAPPER.toModel(entity);
    }

    @Transactional
    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }

    @Transactional
    public Employee updateEmployee(UUID id, EmployeeInput input) {
        var entity = employeeRepository.getOne(id);
        entity.setAge(input.getAge());
        entity.setName(input.getName());
        entity.setPosition(input.getPosition());
        entity.setSalary(input.getSalary());

        var updatedEntity = employeeRepository.save(entity);
        log.info("Employee successfully updated");
        return EMPLOYEE_MAPPER.toModel(updatedEntity);
    }
}
