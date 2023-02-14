package com.abc.ms.service.impl;

import com.abc.ms.dto.APIResponseDto;
import com.abc.ms.dto.DepartmentDto;
import com.abc.ms.dto.EmployeeDto;
import com.abc.ms.entity.Employee;
import com.abc.ms.exception.ResourceNotFoundException;
import com.abc.ms.repository.EmployeeRepository;
import com.abc.ms.service.APIFeignClient;
import com.abc.ms.service.EmployeeService;
import com.abc.ms.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alhagie Bai Cham
 * @date 2/14/23
 */

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Converter converter;
    private final APIFeignClient feignClient;

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {

        Employee save = employeeRepository.save(converter.toEmployee(employeeDto));
        return converter.toDto(save);
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream().map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public APIResponseDto findByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("No employee with email: " + email));
        EmployeeDto employeeDto = converter.toDto(employee);
        DepartmentDto departmentDto = getDepartmentDto(employeeDto);
        return new APIResponseDto(employeeDto, departmentDto);
    }

    @Override
    public APIResponseDto findById(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No employee with id: " + id));
        EmployeeDto employeeDto = converter.toDto(employee);

        DepartmentDto departmentDto = getDepartmentDto(employeeDto);
        return new APIResponseDto(employeeDto, departmentDto);
    }

    private DepartmentDto getDepartmentDto(EmployeeDto employeeDto) {
        return feignClient.findDepartmentByCode(employeeDto.getDepartmentCode());
    }
}
