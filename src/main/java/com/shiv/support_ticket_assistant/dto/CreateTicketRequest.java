package com.shiv.support_ticket_assistant.dto;

import com.shiv.support_ticket_assistant.model.Priority;

public record CreateTicketRequest(String title, String description, Priority priority) {
}
