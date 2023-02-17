package com.abc.ms.service.impl;

import com.abc.ms.dto.APIResponseDto;
import com.abc.ms.dto.DepartmentDto;
import com.abc.ms.dto.EmployeeDto;
import com.abc.ms.entity.Employee;
import com.abc.ms.exception.ResourceNotFoundException;
import com.abc.ms.repository.EmployeeRepository;
import com.abc.ms.service.EmployeeService;
import com.abc.ms.utils.Converter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alhagie Bai Cham
 * @date 2/14/23
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Converter converter;
    private final WebClient client;

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

    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultResponse")
    @Override
    public APIResponseDto findById(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No employee with id: " + id));
        EmployeeDto employeeDto = converter.toDto(employee);

        DepartmentDto departmentDto = getDepartmentDto(employeeDto);
        return new APIResponseDto(employeeDto, departmentDto);
    }

    private DepartmentDto getDepartmentDto(EmployeeDto employeeDto) {
        DepartmentDto response = client.get()
                .uri(uriBuilder -> uriBuilder
                        .host("department-service")
                        .path("/api/departments/" + employeeDto.getDepartmentCode())
                        .build()
                ).retrieve().bodyToMono(DepartmentDto.class).block();
        log.info("Response from department service: {}", response);
        return response;
    }

    public APIResponseDto getDefaultResponse(long id, Exception exception) {
        log.info("Fallback method is called");
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No employee with id: " + id));
        EmployeeDto employeeDto = converter.toDto(employee);

        DepartmentDto departmentDto = new DepartmentDto(Long.MIN_VALUE, "unreachable", "Department is down", "DID");
        return new APIResponseDto(employeeDto, departmentDto);
    }
}
