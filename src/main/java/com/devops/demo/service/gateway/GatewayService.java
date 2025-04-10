package com.devops.demo.service.gateway;

import com.devops.demo.database.entity.gateway.EndpointPermissionsEntity;
import com.devops.demo.database.repository.gatewayRepository.endpointpermissionsrepository.EndpointPermissionsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GatewayService {

    private final EndpointPermissionsRepository endpointPermissionsRepository;

    @Autowired
    public GatewayService(EndpointPermissionsRepository endpointPermissionsRepository) {
        this.endpointPermissionsRepository = endpointPermissionsRepository;
    }

    public Optional<EndpointPermissionsEntity> getPermissionByEndpoint(String endpoint) {
        return Optional.ofNullable(endpointPermissionsRepository.findByEndpoint(endpoint));
    }
}
