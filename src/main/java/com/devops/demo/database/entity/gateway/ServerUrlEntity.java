package com.devops.demo.database.entity.gateway;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "server_url")
public class ServerUrlEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "requestUrl", nullable = false, unique = true)
    private String requestUrl;

    @Column(name = "targetUrl", nullable = false)
    private String targetUrl;

    @Column(name = "method", nullable = false, length = 8)
    private String method;

    @Column(name = "service", nullable = false, length = 50)
    private String service;

    @Column(name = "requestEncrypted", nullable = false)
    private int requestEncrypted;

    @Column(name = "allowUserEdit", nullable = false)
    private int allowUserEdit;

    @Column(name = "permitAll", nullable = false)
    private int permitAll; // ✅ Added missing column
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Getters
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getMethod() {
        return method;
    }

    public String getService() {
        return service;
    }

    public int getRequestEncrypted() {
        return requestEncrypted;
    }

    public int getAllowUserEdit() {
        return allowUserEdit;
    }

    public int getPermitAll() {
        return permitAll;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setRequestEncrypted(int requestEncrypted) {
        this.requestEncrypted = requestEncrypted;
    }

    public void setAllowUserEdit(int allowUserEdit) {
        this.allowUserEdit = allowUserEdit;
    }

    public void setPermitAll(int permitAll) {
        this.permitAll = permitAll;
        
      
    }
    public LocalDateTime getCreatedAt() { return createdAt;}
}
