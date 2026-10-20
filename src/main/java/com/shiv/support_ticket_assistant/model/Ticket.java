package com.shiv.support_ticket_assistant.model;

import java.time.Instant;

public class Ticket {
    private Long id;
    private String title, description;
    private Instant createdAt, updatedAt;
    private Priority priority;
    private TicketStatus status;

    public Ticket(String title, String description, Priority priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = TicketStatus.OPEN;
        createdAt = updatedAt = Instant.now();
    }

    // All Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    // Setters (no setter for createdAt; updatedAt changes only through touch())
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public void touch() {
        updatedAt = Instant.now();
    }

}
