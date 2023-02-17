package com.abc.ms.repository;

import com.abc.ms.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Alhagie Bai Cham
 * @date 2/17/23
 */
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByCode(String code);
}
