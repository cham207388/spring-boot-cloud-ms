package com.abc.ms.service;

import com.abc.ms.dto.OrganizationDto;

/**
 * @author Alhagie Bai Cham
 * @date 2/17/23
 */
public interface OrganizationService {

    OrganizationDto save(OrganizationDto dto);
    OrganizationDto findByCode(String code);
}
