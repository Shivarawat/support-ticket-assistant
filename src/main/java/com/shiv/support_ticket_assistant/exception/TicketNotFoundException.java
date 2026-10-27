package com.shiv.support_ticket_assistant.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long id) {
        super("Ticket " + id + " not found");
    }
}
