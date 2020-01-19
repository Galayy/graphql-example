package com.itechart.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.itechart.model.Employee;
import com.itechart.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.itechart.mapper.EmployeeMapper.EMPLOYEE_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeQueries implements GraphQLQueryResolver {

    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<Employee> employees() {
        log.info("Employees find");
        return employeeRepository.findAll().stream().map(EMPLOYEE_MAPPER::toModel).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Employee> employeesByPosition(String position) {
        log.info("Employees find: position={}", position);
        return employeeRepository.findAllByPosition(position).stream().map(EMPLOYEE_MAPPER::toModel)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Employee employee(UUID id) {
        log.info("EmployeeEntity find: id={}", id);
        return EMPLOYEE_MAPPER.toModel(employeeRepository.findById(id).orElseThrow());
    }

}
