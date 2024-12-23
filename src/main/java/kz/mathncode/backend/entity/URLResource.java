package kz.mathncode.backend.entity;

import java.time.ZonedDateTime;
import java.util.UUID;

public class URLResource {
    private UUID id;
    private String fromAddress;
    private String toAddress;
    private UUID createdBy;
    private ZonedDateTime createdAt;

    public URLResource() {
    }

    public URLResource(UUID id, String fromAddress, String toAddress, UUID createdBy, ZonedDateTime createdAt) {
        this.id = id;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
