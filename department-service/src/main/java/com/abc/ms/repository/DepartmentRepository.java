package com.abc.ms.repository;

import com.abc.ms.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Alhagie Bai Cham
 * @date 2/13/23
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByCode(String code);
}
