package com.abc.ms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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
public class EmployeeDto {
    private Long id;

    @Size(min = 3, message = "At least 3 characters")
    private String firstName;
    @Size(min = 3, message = "At least 3 characters")
    private String lastName;
    @Email(message = "Please provide correct email format")
    private String email;

    private String departmentCode;
}
