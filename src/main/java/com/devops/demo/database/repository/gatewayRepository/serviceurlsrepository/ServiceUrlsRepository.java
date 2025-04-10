package com.devops.demo.database.repository.gatewayRepository.serviceurlsrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devops.demo.database.entity.gateway.ServiceUrlsEntity;

@Repository
public interface ServiceUrlsRepository extends JpaRepository<ServiceUrlsEntity, Long> {
    ServiceUrlsEntity findByServiceName(String serviceName);
}
