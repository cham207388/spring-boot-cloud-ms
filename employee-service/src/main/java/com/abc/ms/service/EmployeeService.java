package com.abc.ms.service;

import com.abc.ms.dto.EmployeeDto;

import java.util.List;

/**
 * @author Alhagie Bai Cham
 * @date 2/14/23
 */
public interface EmployeeService {

    EmployeeDto save(EmployeeDto employeeDto);


    List<EmployeeDto> findAll();

    EmployeeDto findByEmail(String email);

    EmployeeDto findById(long id);
}
