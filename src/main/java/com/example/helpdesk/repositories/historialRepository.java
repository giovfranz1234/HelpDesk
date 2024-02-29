package com.example.helpdesk.repositories;


import com.example.helpdesk.models.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface historialRepository extends JpaRepository<Historial, Long> {
}
