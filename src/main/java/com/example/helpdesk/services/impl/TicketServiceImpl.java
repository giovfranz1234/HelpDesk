package com.example.helpdesk.services.impl;


import com.example.helpdesk.models.Ticket;
import com.example.helpdesk.repositories.ticketRepository;
import com.example.helpdesk.services.TicketService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
   @Autowired
    private ticketRepository ticketRepository;


    @Override
    @Transactional
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }




}


