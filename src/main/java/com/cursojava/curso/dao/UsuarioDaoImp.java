package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.ClientInfoStatus;
import java.util.List;
@Transactional
@Repository
public  class UsuarioDaoImp implements  UsuarioDao {
    @PersistenceContext
    EntityManager entityManager; //conexion coon bd

    @Override
    @Transactional
    public List<Usuario> getUsuario() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();

    }

    @Override

    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);

    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuario  obtenerUsuarioXcredencial(Usuario usuario) {
        String query = "FROM Usuario where mail = :mail "; //para evitar los sql injection, si el mail = al mail q pasan por el parametro de diez
        List<Usuario> lista = entityManager.createQuery(query).setParameter("mail", usuario.getMail()).getResultList();
        if (lista.isEmpty()) {
            return null;
        }
        String passwordHashed = lista.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
          if(argon2.verify(passwordHashed, usuario.getPassword())){
              return lista.get(0);
          }
          return null;



    }
}
