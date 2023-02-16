package com.abc.ms.controller;

import com.abc.ms.dto.DepartmentDto;
import com.abc.ms.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author Alhagie Bai Cham
 * @date 2/13/23
 */

@Slf4j
@Data
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;


    @PostMapping
    public ResponseEntity<DepartmentDto> save(@RequestBody @Valid DepartmentDto dto) {
        DepartmentDto save = departmentService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(save.getCode()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> findAll() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/{code}")
    public ResponseEntity<DepartmentDto> findByCode(@PathVariable String code) {
        DepartmentDto response = departmentService.findByCode(code);
        log.info("Response: {}", response);
        return ResponseEntity.ok(response);
    }
}
