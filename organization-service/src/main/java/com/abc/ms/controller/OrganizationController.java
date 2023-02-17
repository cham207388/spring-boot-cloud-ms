package com.abc.ms.controller;

import com.abc.ms.dto.OrganizationDto;
import com.abc.ms.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @author Alhagie Bai Cham
 * @date 2/17/23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto> save(@RequestBody @Valid OrganizationDto dto) {
        OrganizationDto save = organizationService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> findById(@PathVariable long id) {
        return new ResponseEntity<>(organizationService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<OrganizationDto> findByCode(@PathVariable String code) {
        return new ResponseEntity<>(organizationService.findByCode(code), HttpStatus.OK);
    }
}
