package com.example.helpdesk.services.impl;


import com.example.helpdesk.models.Equipo;
import com.example.helpdesk.repositories.equipoRepository;
import com.example.helpdesk.services.EquipoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {
    @Autowired
    private equipoRepository equipoRepository;
    @Override
    @Transactional
    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    @Override
    public Optional<Equipo> findById(Long id) {
        return equipoRepository.findById(id);
    }

    @Override
    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Override
    public void deleteById(Long id) {
        equipoRepository.deleteById(id);
    }
}
