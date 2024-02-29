package com.example.helpdesk.services.impl;


import com.example.helpdesk.models.Usuario;
import com.example.helpdesk.repositories.usuarioRepository;
import com.example.helpdesk.services.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private usuarioRepository usuarioRepository;

    @Override
    @Transactional
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
         usuarioRepository.deleteById(id);
    }
}
