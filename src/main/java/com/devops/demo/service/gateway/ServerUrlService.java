package com.devops.demo.service.gateway;

import java.util.List;
import java.util.Optional;

import com.devops.demo.database.entity.gateway.ServerUrlEntity;

public interface ServerUrlService {
    Optional<ServerUrlEntity> getServerUrlByRequestUrl(String requestUrl);
    List<ServerUrlEntity> getAllServerUrls();
    void deleteServerUrl(String requestUrl);
}
