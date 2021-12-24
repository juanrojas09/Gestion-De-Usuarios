package com.cursojava.curso.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Column(name = "nombre")
    @Getter
    @Setter
    private String nombre;
    @Column(name = "apellido")
    @Getter
    @Setter
    private String apellido;
    @Column(name = "mail")
    @Getter
    @Setter
    private String mail;
    @Column(name = "pass")
    @Getter
    @Setter
    private String password;
    @Column(name = "telefonos")
    @Getter
    @Setter
    private String telefono;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
