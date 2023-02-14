package com.abc.ms.utils;

import com.abc.ms.dto.EmployeeDto;
import com.abc.ms.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author Alhagie Bai Cham
 * @date 2/14/23
 */
@Component
@RequiredArgsConstructor
public class Converter {

    private final ModelMapper modelMapper;

    public EmployeeDto toDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public Employee toEmployee(EmployeeDto dto) {
        return modelMapper.map(dto, Employee.class);
    }
}
