package com.devops.demo.database.repository.gatewayRepository.endpointpermissionsrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devops.demo.database.entity.gateway.EndpointPermissionsEntity;

@Repository
public interface EndpointPermissionsRepository extends JpaRepository<EndpointPermissionsEntity, Long> {
    EndpointPermissionsEntity findByEndpoint(String endpoint);
}
