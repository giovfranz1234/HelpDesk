package com.example.helpdesk.repositories;

import com.example.helpdesk.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface rolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol>findByDescripcion(String descripcion);
}
