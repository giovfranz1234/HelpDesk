package com.example.helpdesk.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "equipos")
public class Equipo implements Serializable {

    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name="serieActivoFijo")
    private String serieActivoFijo;
    @Column (name="tipoEquipo")
    private String tipoEquipo;
    @Column (name="propietario")
    private String propietario;
    @Column (name="unidadOrganizacional")
    private String unidadOrg;
    @Column (name="marca")
    private String marca;
    @Column (name="observaciones")
    private String observaciones;
}
