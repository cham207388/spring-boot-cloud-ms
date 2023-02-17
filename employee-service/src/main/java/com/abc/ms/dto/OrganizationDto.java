package com.abc.ms.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Alhagie Bai Cham
 * @date 2/17/23
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {
    private Long id;
    private String name;
    private String description;
    private String code;
    private LocalDateTime createdAt;
}
