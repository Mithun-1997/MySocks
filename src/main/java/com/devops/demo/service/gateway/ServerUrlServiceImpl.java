package com.devops.demo.service.gateway;

import com.devops.demo.database.entity.gateway.ServerUrlEntity;
import com.devops.demo.database.repository.gatewayRepository.serverurlrepository.ServerUrlRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServerUrlServiceImpl implements ServerUrlService {

    private final ServerUrlRepository serverUrlRepository;

    public ServerUrlServiceImpl(ServerUrlRepository serverUrlRepository) {
        this.serverUrlRepository = serverUrlRepository;
    }

    @Override
    public Optional<ServerUrlEntity> getServerUrlByRequestUrl(String requestUrl) {
        return serverUrlRepository.findByRequestUrl(requestUrl);
    }

    @Override
    public List<ServerUrlEntity> getAllServerUrls() {
        return serverUrlRepository.findAll();
    }

    @Override
    public void deleteServerUrl(String requestUrl) {
        serverUrlRepository.findByRequestUrl(requestUrl)
            .ifPresent(serverUrlRepository::delete);
    }
}
