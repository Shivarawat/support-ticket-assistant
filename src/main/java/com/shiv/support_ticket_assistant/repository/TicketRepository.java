package com.shiv.support_ticket_assistant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiv.support_ticket_assistant.model.Ticket;
import com.shiv.support_ticket_assistant.model.TicketStatus;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Derived query: Spring reads the method name and builds "where status = ?"
    List<Ticket> findByStatus(TicketStatus status);
}
