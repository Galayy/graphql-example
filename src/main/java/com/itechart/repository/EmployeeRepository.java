package com.itechart.repository;

import com.itechart.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {

    List<EmployeeEntity> findAllByPosition(String position);
}
