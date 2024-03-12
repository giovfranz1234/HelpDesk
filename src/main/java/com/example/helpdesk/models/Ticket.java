package com.example.helpdesk.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="idEquipo")
    private Long dispositivo;
    @Column(name="idTecnico")
    private Long asignadoa;
    @Column(name="estado")
    private String estado;
    @Column(name="prioridad")
    private String prioridad;
    @Column(name="idUsuario")
    private Long creadoPor;
    @Column(name="fechaReg")
    private Date fechaRegistro;
    @Column(name="fechaInicio")
    private Date fechaInicio;
    @Column(name="fechaFin")
    private Date fechaFin;
    @Column(name="idHistorial")
    private Long historial;
}

