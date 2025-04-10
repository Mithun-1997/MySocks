package com.devops.demo.controller;

import com.devops.demo.database.entity.gateway.ServerUrlEntity;
import com.devops.demo.service.gateway.ServerUrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/server")
public class ServerUrlController {

    private final ServerUrlService serverUrlService;

    public ServerUrlController(ServerUrlService serverUrlService) {
        this.serverUrlService = serverUrlService;
    }

    @GetMapping("/urls")
    public ResponseEntity<List<ServerUrlEntity>> getAllUrls() {
        return ResponseEntity.ok(serverUrlService.getAllServerUrls());
    }

    @GetMapping("/url/{requestUrl}")
    public ResponseEntity<?> getUrlMapping(@PathVariable String requestUrl) {
        Optional<ServerUrlEntity> urlEntity = serverUrlService.getServerUrlByRequestUrl(requestUrl);
        return urlEntity.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/url/{requestUrl}")
    public ResponseEntity<String> deleteUrl(@PathVariable String requestUrl) {
        serverUrlService.deleteServerUrl(requestUrl);
        return ResponseEntity.ok("Deleted successfully");
    }
}
