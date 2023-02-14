package com.abc.ms.service.impl;

import com.abc.ms.dto.EmployeeDto;
import com.abc.ms.entity.Employee;
import com.abc.ms.repository.EmployeeRepository;
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

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {

        var save = employeeRepository.save(converter.toEmployee(employeeDto));
        return converter.toDto(save);
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream().map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email).get();
        return converter.toDto(employee);
    }

    @Override
    public EmployeeDto findById(long id) {
        Employee employee = employeeRepository.findById(id).get();
        return converter.toDto(employee);
    }
}
