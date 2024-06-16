package com.example.helpdesk.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "logTrans")
public class Log implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name="idUsuario")
    private Long idUsuario;
    @Column (name="transaccion")
    private String transaccion;
    @Column (name="fecha")
    private Date fecha;
    @Column (name="campo")
    private String campo;
    @Column (name="valorNuevo")
    private String valorNuevo;
    @Column (name="valorAnterior")
    private String valorAnterior;
}
