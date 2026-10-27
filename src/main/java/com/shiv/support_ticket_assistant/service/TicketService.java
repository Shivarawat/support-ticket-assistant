package com.shiv.support_ticket_assistant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shiv.support_ticket_assistant.dto.CreateTicketRequest;
import com.shiv.support_ticket_assistant.dto.TicketResponse;
import com.shiv.support_ticket_assistant.dto.UpdateTicketRequest;
import com.shiv.support_ticket_assistant.exception.TicketNotFoundException;
import com.shiv.support_ticket_assistant.model.Ticket;
import com.shiv.support_ticket_assistant.repository.TicketRepository;

@Service
public class TicketService {
    private final TicketRepository repository;

    // Constructor injection: Spring passes in its one TicketRepository
    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public TicketResponse create(CreateTicketRequest req) {
        Ticket ticket = new Ticket(req.title(), req.description(), req.priority());
        return TicketResponse.from(repository.save(ticket));
    }

    public List<TicketResponse> findAll() {
        return repository.findAll().stream().map(TicketResponse::from).toList();
    }

    public TicketResponse findById(Long id) {
        return TicketResponse.from(getOrThrow(id));
    }

    public TicketResponse update(Long id, UpdateTicketRequest req) {
        Ticket ticket = getOrThrow(id);
        ticket.setTitle(req.title());
        ticket.setDescription(req.description());
        ticket.setPriority(req.priority());
        ticket.setStatus(req.status());
        ticket.touch();
        return TicketResponse.from(repository.save(ticket));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new TicketNotFoundException(id);
        }
        repository.deleteById(id);
    }

    // Shared by findById and update: returns the real Ticket (with setters), not the read-only DTO
    private Ticket getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new TicketNotFoundException(id));
    }
}
