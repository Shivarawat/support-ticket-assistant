package com.shiv.support_ticket_assistant.dto;

import com.shiv.support_ticket_assistant.model.Priority;
import com.shiv.support_ticket_assistant.model.TicketStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTicketRequest(
        @NotBlank(message = "title is required")
        @Size(max = 50, message = "title must be at most 50 characters")
        String title,

        @Size(max = 2000, message = "description must be at most 2000 characters")
        String description,

        @NotNull(message = "priority is required")
        Priority priority,

        @NotNull(message = "status is required")
        TicketStatus status) {
}
