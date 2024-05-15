package com.example.helpdesk.services;



import com.example.helpdesk.models.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoService {
    public List<Equipo> findAll();

    public Optional<Equipo> findById(Long id);

    public Equipo save(Equipo equipo);
    public void deleteById(Long id);
    public Optional<Equipo> findBYActivoFijo(String activoFijo);
}
