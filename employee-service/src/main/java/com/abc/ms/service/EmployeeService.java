package com.abc.ms.service;

import com.abc.ms.dto.APIResponseDto;
import com.abc.ms.dto.EmployeeDto;

import java.util.List;

/**
 * @author Alhagie Bai Cham
 * @date 2/14/23
 */
public interface EmployeeService {
    String DEPARTMENT_URL = "http://localhost:8080/api/departments/";

    EmployeeDto save(EmployeeDto employeeDto);


    List<EmployeeDto> findAll();

    APIResponseDto findByEmail(String email);

    APIResponseDto findById(long id);
}
