package com.devops.demo.database.entity.project;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "SPRING_SESSION_ATTRIBUTES", schema = "project")
public class SpringSessionAttributesEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private SpringSessionAttributesId id; // Embedded composite key

    @Lob
    @Column(name = "ATTRIBUTE_BYTES")
    private byte[] attributeBytes;

    // Embeddable class for the composite key
    @Embeddable
    public static class SpringSessionAttributesId implements Serializable {
        private static final long serialVersionUID = 1L; // Added serialVersionUID

        @Column(name = "SESSION_PRIMARY_ID", nullable = false, length = 255)
        private String sessionPrimaryId;

        @Column(name = "ATTRIBUTE_NAME", nullable = false, length = 255)
        private String attributeName;

        // Default constructor (required by JPA)
        public SpringSessionAttributesId() {
        }

        // Parameterized constructor
        public SpringSessionAttributesId(String sessionPrimaryId, String attributeName) {
            this.sessionPrimaryId = sessionPrimaryId;
            this.attributeName = attributeName;
        }

        // Getters and Setters
        public String getSessionPrimaryId() {
            return sessionPrimaryId;
        }

        public void setSessionPrimaryId(String sessionPrimaryId) {
            this.sessionPrimaryId = sessionPrimaryId;
        }

        public String getAttributeName() {
            return attributeName;
        }

        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }

        // Implement equals() and hashCode()
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SpringSessionAttributesId that = (SpringSessionAttributesId) o;
            return Objects.equals(sessionPrimaryId, that.sessionPrimaryId) &&
                   Objects.equals(attributeName, that.attributeName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sessionPrimaryId, attributeName);
        }
    }

    // Default constructor (required by JPA)
    public SpringSessionAttributesEntity() {}

    // Parameterized constructor
    public SpringSessionAttributesEntity(String sessionPrimaryId, String attributeName, byte[] attributeBytes) {
        this.id = new SpringSessionAttributesId(sessionPrimaryId, attributeName);
        this.attributeBytes = attributeBytes;
    }

    // Getters and Setters
    public SpringSessionAttributesId getId() {
        return id;
    }

    public void setId(SpringSessionAttributesId id) {
        this.id = id;
    }

    public String getSessionPrimaryId() {
        return id != null ? id.getSessionPrimaryId() : null;
    }

    public void setSessionPrimaryId(String sessionPrimaryId) {
        if (id == null) {
            id = new SpringSessionAttributesId();
        }
        id.setSessionPrimaryId(sessionPrimaryId);
    }

    public String getAttributeName() {
        return id != null ? id.getAttributeName() : null;
    }

    public void setAttributeName(String attributeName) {
        if (id == null) {
            id = new SpringSessionAttributesId();
        }
        id.setAttributeName(attributeName);
    }

    public byte[] getAttributeBytes() {
        return attributeBytes;
    }

    public void setAttributeBytes(byte[] attributeBytes) {
        this.attributeBytes = attributeBytes;
    }
}