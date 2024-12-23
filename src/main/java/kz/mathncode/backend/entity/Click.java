package kz.mathncode.backend.entity;


import java.time.ZonedDateTime;
import java.util.UUID;

public class Click {
    private UUID id;
    private UUID resourceId;
    private String ipAddress;
    private ZonedDateTime createdAt;

    public Click() {
    }

    public Click(UUID id, UUID resourceId, String ipAddress, ZonedDateTime createdAt) {
        this.id = id;
        this.resourceId = resourceId;
        this.ipAddress = ipAddress;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getResourceId() {
        return resourceId;
    }

    public void setResourceId(UUID resourceId) {
        this.resourceId = resourceId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
