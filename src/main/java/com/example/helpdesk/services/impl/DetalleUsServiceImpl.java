package com.example.helpdesk.services.impl;

import com.example.helpdesk.models.Usuario;
import com.example.helpdesk.repositories.usuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetalleUsServiceImpl implements UserDetailsService {
    @Autowired
    private usuarioRepository usuarioRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername (String login)  throws UsernameNotFoundException {

        Optional<Usuario> optionalUsuario =  usuarioRepository.findByLogin(login);
        if (optionalUsuario.isEmpty()) {
            throw new UsernameNotFoundException(String.format("usuario %s no existe en el sistema", login));
        }
        Usuario usuario = optionalUsuario.get();
        List<GrantedAuthority> autorizado = usuario.getRoles()
                .stream()
                .map(rol-> new SimpleGrantedAuthority(rol.getDescripcion()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(login,
                usuario.getPassword(),
                true,
                true,
                true,
                true,
                autorizado);
    }
}



