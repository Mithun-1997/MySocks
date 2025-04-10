package com.devops.demo.database.permission;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "endpoint_permissions", schema = "gateway") // ✅ If it's a Gateway entity
public class EndpointPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String endpoint;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PermissionType permission;

    // Default constructor (Required by JPA)
    public EndpointPermission() {}

    // Parameterized constructor
    public EndpointPermission(String endpoint, PermissionType permission) {
        this.endpoint = endpoint;
        this.permission = permission;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

    public PermissionType getPermission() { return permission; }
    public void setPermission(PermissionType permission) { this.permission = permission; }

    // Equals & HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndpointPermission that = (EndpointPermission) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(endpoint, that.endpoint) &&
               permission == that.permission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endpoint, permission);
    }
}
