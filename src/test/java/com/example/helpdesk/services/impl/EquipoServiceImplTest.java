package com.example.helpdesk.services.impl;

import com.example.helpdesk.models.Equipo;
import com.example.helpdesk.repositories.equipoRepository;
import com.example.helpdesk.services.EquipoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


/*class EquipoServiceImplTest {
 /*   @Mock
    private equipoRepository equipoRepository;
    @InjectMocks
    private EquipoService equipoService;

    private Equipo equipo;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        equipo=new Equipo();


    }

    @Test
    void findAll() {
        when(equipoService.findAll()).thenReturn(Arrays.asList(equipo));

    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findBYActivoFijo() {
    }
}*/