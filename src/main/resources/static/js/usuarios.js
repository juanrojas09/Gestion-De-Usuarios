// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuarios();
 $("#usuarios").DataTable();
 actualizarNombreUsuario();



});
function actualizarNombreUsuario(){
  document.getElementById('txt-email-usuario').outerHTML=localStorage.mail;
}

async function cargarUsuarios() {

  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization':localStorage.token
    }

  });
  const usuarios = await request.json();
let listadohtml=''

  for(let usuario of usuarios){
    let botoneliminar='<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
let telefonotexto=usuario.telefono == null ? '-' : usuario.telefono;
    let usuariohtml='<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido+'</td><td>'+usuario.mail+'</td><td>'+telefonotexto+'</td><td>'+botoneliminar+'</td></tr>';

    listadohtml+=usuariohtml;

  }





  document.querySelector('#usuarios tbody').outerHTML=listadohtml;

}

async function eliminarUsuario(id) { //ver porque al eliminar, no me borra de la bd y me tira un bad request
  if(!confirm("Desea eliminar el usuario?")){
    return;
  }
  const request = await fetch('api/usuario/'+id, {
    method: 'DELETE',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization':localStorage.token
    }

  });

  location.reload();


}

