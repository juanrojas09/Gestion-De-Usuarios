package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface UsuarioDao { //las interfaces son archivos donde indicamos las funciones que deberia tener una clase


    @Transactional
    List<Usuario> getUsuario();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioXcredencial(Usuario usuario);
}
