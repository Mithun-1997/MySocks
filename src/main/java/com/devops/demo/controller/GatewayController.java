package com.devops.demo.controller;

import com.devops.demo.database.entity.gateway.ServerUrlEntity;
import com.devops.demo.service.gateway.ServerUrlService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/server")
public class GatewayController {

    private final ServerUrlService serverUrlService;
    private final RestTemplate restTemplate;

    public GatewayController(ServerUrlService serverUrlService, RestTemplate restTemplate) {
        this.serverUrlService = serverUrlService;
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<?> handleDynamicRequests(
            @RequestHeader Map<String, String> headers,
            @RequestParam Map<String, String> requestParams,
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestBody(required = false) String requestBody
    ) {
        // Extract request URL from headers
        String requestUrl = headers.get("Request-URL");

        if (requestUrl == null) {
            return ResponseEntity.badRequest().body("Missing Request-URL header");
        }

        // Fetch mapping from database
        Optional<ServerUrlEntity> apiMapping = serverUrlService.getServerUrlByRequestUrl(requestUrl);
        if (apiMapping.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("API Not Found");
        }

        ServerUrlEntity serverUrl = apiMapping.get();
        String targetUrl = serverUrl.getTargetUrl();

        // Replace path variables if any
        for (Map.Entry<String, String> param : requestParams.entrySet()) {
            targetUrl = targetUrl.replace("{" + param.getKey() + "}", param.getValue());
        }

        // Check authentication
        if (serverUrl.getPermitAll() == 0 && (authorizationHeader == null || authorizationHeader.isEmpty())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Required");
        }

        // Set up headers
        HttpHeaders outgoingHeaders = new HttpHeaders();
        headers.forEach(outgoingHeaders::set);

        if (authorizationHeader != null) {
            outgoingHeaders.set("Authorization", authorizationHeader);
        }

        // Determine HTTP method
        HttpMethod method;
        try {
            method = HttpMethod.valueOf(serverUrl.getMethod().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.badRequest().body("Invalid or missing HTTP method in database");
        }

        HttpEntity<String> entity = new HttpEntity<>(requestBody, outgoingHeaders);

        // Forward the request
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(targetUrl, method, entity, String.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error forwarding request: " + e.getMessage());
        }

        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }
}
