package com.abc.ms.dto;

import lombok.*;

/**
 * @author Alhagie Bai Cham
 * @date 2/14/23
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    private String name;
    private String description;
    private String code;
}
