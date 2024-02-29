package com.example.helpdesk.repositories;


import com.example.helpdesk.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ticketRepository extends JpaRepository<Ticket, Long> {





}
