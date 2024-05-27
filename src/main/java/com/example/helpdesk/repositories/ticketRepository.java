package com.example.helpdesk.repositories;



import com.example.helpdesk.controllers.beans.GraficosBean;
import com.example.helpdesk.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ticketRepository extends JpaRepository<Ticket, Long> {


    @Query("SELECT new com.example.helpdesk.controllers.beans.GraficosBean(u.asignadoa, COUNT(u.asignadoa)) " +
            "FROM Ticket u WHERE u.estado <> 'NA' GROUP BY u.asignadoa")
    List<GraficosBean> countAsig();


}
