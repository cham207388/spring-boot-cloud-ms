package com.abc.ms.service;

import com.abc.ms.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Alhagie Bai Cham
 * @date 2/14/23
 */
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIFeignClient {

    @GetMapping("api/departments/{code}")
    DepartmentDto findDepartmentByCode(@PathVariable String code);
}
