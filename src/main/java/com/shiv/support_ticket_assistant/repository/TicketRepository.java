package com.shiv.support_ticket_assistant.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.shiv.support_ticket_assistant.model.Ticket;

@Repository
public class TicketRepository {
    private final ConcurrentHashMap<Long, Ticket> map = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public Ticket save(Ticket ticket) {
        if (ticket.getId() == null) {
            ticket.setId(counter.getAndIncrement());
        }
        map.put(ticket.getId(), ticket);
        return ticket;
    }

    public Optional<Ticket> findById(Long id) {
        return Optional.ofNullable(map.get(id));
    }

    public List<Ticket> findAll() {
        return new ArrayList<>(map.values());
    }

    public boolean existsById(Long id) {
        return map.containsKey(id);
    }

    public void deleteById(Long id) {
        map.remove(id);
    }
}
