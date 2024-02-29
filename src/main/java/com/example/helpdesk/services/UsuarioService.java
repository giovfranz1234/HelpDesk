package com.example.helpdesk.services;



import com.example.helpdesk.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public List<Usuario> findAll();

    public Optional<Usuario> findById(Long id);

    public Usuario save(Usuario usuario);
    public void deleteById(Long id);


}
