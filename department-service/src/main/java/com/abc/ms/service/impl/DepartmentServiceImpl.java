package com.abc.ms.service.impl;

import com.abc.ms.dto.DepartmentDto;
import com.abc.ms.entity.Department;
import com.abc.ms.exception.ResourceNotFoundException;
import com.abc.ms.repository.DepartmentRepository;
import com.abc.ms.service.DepartmentService;
import com.abc.ms.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alhagie Bai Cham
 * @date 2/13/23
 */

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final Converter converter;

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        Department department = converter.toDepartment(departmentDto);
        return converter.toDto(departmentRepository.save(department));
    }

    @Override
    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll()
                .stream().map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto findByCode(String code) {
        Department department = departmentRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("No department with code: " + code));
        return converter.toDto(department);
    }
}
