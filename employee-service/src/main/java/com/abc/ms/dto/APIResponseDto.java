package com.abc.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alhagie Bai Cham
 * @date 2/14/23
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseDto {

    private EmployeeDto employee;
    private DepartmentDto department;
}
