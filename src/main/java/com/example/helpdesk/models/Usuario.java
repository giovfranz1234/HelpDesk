package com.example.helpdesk.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name="nombres")
    private String nombres;
    @Column (name="apPaterno")
    private String paterno;
    @Column (name="apMaterno")
    private String materno;
    @Column (name="docIdentidad")
    private String docIdentidad;
    @Column (name="correo")
    private String correoElectronico;
    @Column (name="telefono")
    private String telefono;
    @Column (name="rol")
    private String rol;
    @Column (name="estado")
    private String estado;
}
