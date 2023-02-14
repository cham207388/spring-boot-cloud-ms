package com.abc.ms.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alhagie Bai Cham
 * @date 2/13/23
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;
    @Size(min = 3, message = "Name should be at least 3 characters")
    private String name;
    private String description;

    @Size(min = 5, message = "Code should be 5 characters only")
    private String code;
}
