package com.shiv.support_ticket_assistant.dto;

import java.time.Instant;

import com.shiv.support_ticket_assistant.model.Priority;
import com.shiv.support_ticket_assistant.model.Ticket;
import com.shiv.support_ticket_assistant.model.TicketStatus;

public record TicketResponse(Long id, String title, String description, Priority priority,
        TicketStatus status, Instant createdAt, Instant updatedAt) {

    // Converts the internal Ticket into what the client sees
    public static TicketResponse from(Ticket t) {
        return new TicketResponse(t.getId(), t.getTitle(), t.getDescription(), t.getPriority(),
                t.getStatus(), t.getCreatedAt(), t.getUpdatedAt());
    }
}
