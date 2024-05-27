package com.example.helpdesk.services;


import com.example.helpdesk.controllers.beans.GraficosBean;
import com.example.helpdesk.models.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    public List<Ticket> findAll();

    public Optional<Ticket> findById(Long id);

    public Ticket save(Ticket ticket);
    public void deleteById(Long id);

    public List<GraficosBean>  GraficoAsig();
}
