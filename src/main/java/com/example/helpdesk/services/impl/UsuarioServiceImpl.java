package com.example.helpdesk.services.impl;



import com.example.helpdesk.models.IUser;
import com.example.helpdesk.models.Rol;
import com.example.helpdesk.models.Usuario;
import com.example.helpdesk.repositories.rolRepository;
import com.example.helpdesk.repositories.usuarioRepository;
import com.example.helpdesk.services.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.catalina.realm.UserDatabaseRealm.getRoles;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private usuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private rolRepository rolRepository;
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
        usuario.setRoles(getRoles(usuario));
        if(usuario.getPassword()!=null){
         usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
         usuarioRepository.deleteById(id);
    }

    private List<Rol> getRoles(IUser user) {
        List<Rol> roles = new ArrayList<>();
        Optional<Rol> optionalRoleUser = rolRepository.findByDescripcion ("ROLE_USUARIO");
        optionalRoleUser.ifPresent(roles::add);

        if (user.isAdmin()) {
            Optional<Rol> optionalRoleAdmin = rolRepository.findByDescripcion("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        return roles;
    }



}
