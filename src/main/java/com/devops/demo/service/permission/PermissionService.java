package com.devops.demo.service.permission;

import com.devops.demo.database.entity.gateway.EndpointPermissionsEntity;
import com.devops.demo.database.repository.gatewayRepository.endpointpermissionsrepository.EndpointPermissionsRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    private final EndpointPermissionsRepository endpointPermissionsRepository;

    public PermissionService(EndpointPermissionsRepository endpointPermissionsRepository) {
        this.endpointPermissionsRepository = endpointPermissionsRepository;
    }

    public List<String> getProtectedEndpoints() {
        return endpointPermissionsRepository.findAll().stream()
                .map(EndpointPermissionsEntity::getEndpoint)
                .toList();
    }
}
