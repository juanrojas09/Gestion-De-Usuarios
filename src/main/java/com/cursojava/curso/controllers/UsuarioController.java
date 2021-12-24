package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
public class UsuarioController {

    @Autowired //hace que el imp dao cree un objeto y lo guarda en la variable
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET) //el maping es el url/pruebas
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("juan");
        usuario.setApellido("rojas");
        usuario.setMail("juan@gmail.com");
        usuario.setPassword("123");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios") //el maping es el url/pruebas
    public List<Usuario> getUsuarios(/*@RequestHeader(value = "Authorization") String token*/) {
        return usuarioDao.getUsuario();
/*if(validarToken(token)){
    return null;
}*/

    }

    private boolean validarToken(String token){
        String idUsuario = jwtUtil.getKey(token);
        return idUsuario !=null;
    }


    @RequestMapping(value="usuario1") //el maping es el url/pruebas
    public Usuario EditarUsuario(){
       Usuario usuario=new Usuario();
       usuario.setNombre("juan");
       usuario.setApellido("rojas");
       usuario.setMail("juan@gmail.com");
       usuario.setPassword("123");
       return usuario;
    }
    @RequestMapping(value="api/usuario/{id}",method= RequestMethod.DELETE) //el maping es el url/pruebas
    public void eliminar(@PathVariable("id") Long id/*@RequestHeader(value = "Authorization") String token*/){
        /*if(validarToken(token)){
            return ;
        }*/
        usuarioDao.eliminar(id);



    }




    @RequestMapping(value="api/usuarios",method = RequestMethod.POST) //el maping es el url/pruebas
    public void registrarUsuario(@RequestBody Usuario usuario){
        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
       String hash= argon2.hash(1,1024,1,usuario.getPassword());//cuantas iteracionesde hasheo
        usuario.setPassword(hash);
         usuarioDao.registrar(usuario);
    }


    @RequestMapping(value="api/login",method = RequestMethod.POST) //el maping es el url/pruebas
    public String login(@RequestBody Usuario usuario){

        Usuario usuarioLogueado= usuarioDao.obtenerUsuarioXcredencial(usuario);
        if(usuarioLogueado!=null){


            String tokenJwt=  jwtUtil.create(String.valueOf(usuarioLogueado.getId()),usuarioLogueado.getMail());
            return tokenJwt;



        } return "failed";
    }

}
