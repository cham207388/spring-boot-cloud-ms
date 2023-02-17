package com.abc.ms.service.impl;

import com.abc.ms.dto.OrganizationDto;
import com.abc.ms.entity.Organization;
import com.abc.ms.exception.ResourceNotFoundException;
import com.abc.ms.repository.OrganizationRepository;
import com.abc.ms.service.OrganizationService;
import com.abc.ms.utils.Formatter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @author Alhagie Bai Cham
 * @date 2/17/23
 */
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrganizationDto save(OrganizationDto dto) {
        Organization organization = modelMapper.map(dto, Organization.class);
        Organization save = organizationRepository.save(organization);
        return modelMapper.map(save, OrganizationDto.class);
    }

    @Override
    public OrganizationDto findById(long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Formatter.idErrorMessage(id)));
        return modelMapper.map(organization, OrganizationDto.class);
    }

    @Override
    public OrganizationDto findByCode(String code) {
        Organization organization = organizationRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException(Formatter.codeErrorMessage(code)));
        return modelMapper.map(organization, OrganizationDto.class);
    }
}
