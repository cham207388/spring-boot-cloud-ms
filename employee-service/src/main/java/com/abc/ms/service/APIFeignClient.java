//package com.abc.ms.service;
//
//import com.abc.ms.dto.DepartmentDto;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import static com.abc.ms.service.EmployeeService.DEPARTMENT_URL;
//
///**
// * @author Alhagie Bai Cham
// * @date 2/14/23
// */
//@FeignClient(url = DEPARTMENT_URL, value = "DEPARTMENT-SERVICE")
//@Component
//public interface APIFeignClient {
//
//    @GetMapping("{code}")
//    DepartmentDto findDepartmentByCode(@PathVariable String code);
//}
