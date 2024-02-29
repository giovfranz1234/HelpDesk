package com.example.helpdesk.repositories;


import com.example.helpdesk.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface usuarioRepository extends JpaRepository<Usuario, Long> {

}
