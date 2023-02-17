package com.abc.ms.service.impl;

import com.abc.ms.dto.APIResponseDto;
import com.abc.ms.dto.DepartmentDto;
import com.abc.ms.dto.EmployeeDto;
import com.abc.ms.dto.OrganizationDto;
import com.abc.ms.entity.Employee;
import com.abc.ms.exception.ResourceNotFoundException;
import com.abc.ms.repository.EmployeeRepository;
import com.abc.ms.service.EmployeeService;
import com.abc.ms.utils.Converter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.abc.ms.utils.Constant.*;

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
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public APIResponseDto findByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("No employee with email: " + email));
        return getApiResponseDto(employee);
    }

    @NotNull
    private APIResponseDto getApiResponseDto(Employee employee) {
        EmployeeDto employeeDto = converter.toDto(employee);
        DepartmentDto departmentDto = getDepartmentDto(employeeDto.getDepartmentCode());
        OrganizationDto organizationDto = getOrganizationDto(employeeDto.getOrganizationCode());
        return new APIResponseDto(employeeDto, departmentDto, organizationDto);
    }

    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultResponse")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultResponse")
    @Override
    public APIResponseDto findById(long id) {
        log.info("findById method! id : {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No employee with id: " + id));
        return getApiResponseDto(employee);
    }

    private DepartmentDto getDepartmentDto(String departmentCode) {
        log.info("Getting department by code: {}", departmentCode);
        DepartmentDto response = client.get()
                .uri(uriBuilder -> uriBuilder
                        .host(DEPARTMENT_SERVICE)
                        .path(deptPath(departmentCode))
                        .build()
                ).retrieve().bodyToMono(DepartmentDto.class).block();
        log.info("Response from department service: {}", response);
        return response;
    }

    private OrganizationDto getOrganizationDto(String organizationCode) {
        log.info("Getting organization by code: {}", organizationCode);
        OrganizationDto response = client.get()
                .uri(uriBuilder -> uriBuilder
                        .host(ORGANIZATION_SERVICE)
                        .path(orgPath(organizationCode))
                        .build()
                ).retrieve().bodyToMono(OrganizationDto.class).block();
        log.info("Response from organization service: {}", response);
        return response;
    }

    public APIResponseDto getDefaultResponse(long id, Exception exception) {
        log.info("Fallback method is called");
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No employee with id: " + id));
        EmployeeDto employeeDto = converter.toDto(employee);

        DepartmentDto departmentDto = new DepartmentDto(0L, "unreachable", "department is down", "DEPT");
        OrganizationDto organizationDto = new OrganizationDto(0L, "unreachable", "organization is down", "ORG", LocalDateTime.now());
        return new APIResponseDto(employeeDto, departmentDto, organizationDto);
    }
}
