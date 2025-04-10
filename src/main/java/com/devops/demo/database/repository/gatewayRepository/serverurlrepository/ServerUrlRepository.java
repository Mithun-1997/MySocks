package com.devops.demo.database.repository.gatewayRepository.serverurlrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devops.demo.database.entity.gateway.ServerUrlEntity;

import java.util.Optional;

@Repository
public interface ServerUrlRepository extends JpaRepository<ServerUrlEntity, Long> {
    Optional<ServerUrlEntity> findByRequestUrl(String requestUrl);
}
