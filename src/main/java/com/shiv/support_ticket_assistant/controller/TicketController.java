package com.shiv.support_ticket_assistant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shiv.support_ticket_assistant.dto.CreateTicketRequest;
import com.shiv.support_ticket_assistant.dto.TicketResponse;
import com.shiv.support_ticket_assistant.dto.UpdateTicketRequest;
import com.shiv.support_ticket_assistant.model.TicketStatus;
import com.shiv.support_ticket_assistant.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    // Constructor injection: Spring passes in its one TicketService
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse create(@Valid @RequestBody CreateTicketRequest req) {
        return ticketService.create(req);
    }

    // GET /api/tickets              -> all tickets
    // GET /api/tickets?status=OPEN  -> only OPEN tickets
    @GetMapping
    public List<TicketResponse> findAll(@RequestParam(required = false) TicketStatus status) {
        if (status == null) {
            return ticketService.findAll();
        }
        return ticketService.findByStatus(status);
    }

    @GetMapping("/{id}")
    public TicketResponse findById(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @PutMapping("/{id}")
    public TicketResponse update(@PathVariable Long id, @Valid @RequestBody UpdateTicketRequest req) {
        return ticketService.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        ticketService.delete(id);
    }
}
