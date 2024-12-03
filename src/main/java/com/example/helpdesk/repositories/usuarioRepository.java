package com.example.helpdesk.repositories;
import com.example.helpdesk.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface usuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario>findByLogin(String login);
}
