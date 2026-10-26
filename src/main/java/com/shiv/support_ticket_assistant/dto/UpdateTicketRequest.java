package com.shiv.support_ticket_assistant.dto;

import com.shiv.support_ticket_assistant.model.Priority;
import com.shiv.support_ticket_assistant.model.TicketStatus;

public record UpdateTicketRequest(String title, String description, Priority priority, TicketStatus status) {

}
