package com.example.helpdesk.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements IUser {

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
    @Column (name="login")
    private String login;
    @Column (name="password")
    private String password;
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;
    @JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "roles_usuarios",
            joinColumns = {@JoinColumn(name="usuario_id")},
            inverseJoinColumns = @JoinColumn(name="rol_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames  ={"usuario_id","rol_id"})}
    )
    private List<Rol> roles;
    public List<Rol> getRoles() {
        return roles;
    }
    public Usuario() {
        this.roles = new ArrayList<>();
    }
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
