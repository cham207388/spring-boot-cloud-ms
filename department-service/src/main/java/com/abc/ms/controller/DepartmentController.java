package com.abc.ms.controller;

import com.abc.ms.dto.DepartmentDto;
import com.abc.ms.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author Alhagie Bai Cham
 * @date 2/13/23
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;


    @PostMapping
    public ResponseEntity<DepartmentDto> save(@RequestBody DepartmentDto dto) {
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
        return ResponseEntity.ok(departmentService.findByCode(code));
    }
}
