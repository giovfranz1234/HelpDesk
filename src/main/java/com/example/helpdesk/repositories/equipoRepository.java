package com.example.helpdesk.repositories;


import com.example.helpdesk.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface equipoRepository extends JpaRepository<Equipo, Long> {

     Optional<Equipo> findBySerieActivoFijo(String activoFijo);
}

