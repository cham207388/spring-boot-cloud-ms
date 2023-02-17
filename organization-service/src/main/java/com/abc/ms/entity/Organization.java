package com.abc.ms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * @author Alhagie Bai Cham
 * @date 2/17/23
 */
@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Size(min = 3, max = 25, message = "min = 3 and max = 25")
    private String name;
    private String description;
    @Size(min = 5, max = 5, message = "only 5 characters and should be unique")
    @Column(nullable = false, unique = true)
    private String code;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
