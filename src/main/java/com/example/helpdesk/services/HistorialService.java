package com.example.helpdesk.services;



import com.example.helpdesk.models.Historial;

import java.util.List;
import java.util.Optional;

public interface HistorialService {
    public List<Historial> findAll();

    public Optional<Historial> findById(Long id);

    public Historial save(Historial usuario);
    public void deleteById(Long id);
}
