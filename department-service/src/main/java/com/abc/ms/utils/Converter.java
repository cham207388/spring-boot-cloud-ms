package com.abc.ms.utils;

import com.abc.ms.dto.DepartmentDto;
import com.abc.ms.entity.Department;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author Alhagie Bai Cham
 * @date 2/13/23
 */
@Component
@RequiredArgsConstructor
public class Converter {

    private final ModelMapper modelMapper;


    public DepartmentDto toDto(Department department) {
        return modelMapper.map(department, DepartmentDto.class);
    }

    public Department toDepartment(DepartmentDto dto) {
        return modelMapper.map(dto, Department.class);
    }
}
