package com.abc.ms.service;

import com.abc.ms.dto.DepartmentDto;

import java.util.List;

/**
 * @author Alhagie Bai Cham
 * @date 2/13/23
 */
public interface DepartmentService {

    DepartmentDto save(DepartmentDto departmentDto);

    List<DepartmentDto> findAll();

    DepartmentDto findByCode(String code);
}
