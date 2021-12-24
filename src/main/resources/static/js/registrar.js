$(document).ready(function() {
   // registrarUsuarios();




});

async function registrarUsuarios() {
let datos={};
    datos.nombre=document.getElementById('txtNombre').value;
    datos.apellido=document.getElementById('txtApellido').value;
    datos.mail=document.getElementById('txtEmail').value;
    datos.password= document.getElementById('txtPassword').value;

    let repetirpass=document.getElementById("txtPassword1").value;
    if(repetirpass!=datos.password){
        alert("las password son diferentes");
        return;

    }

    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)

    });
    alert("usuario registrado correctamente");
    const usuarios = await request.json();
window.location.href='login.html',true;

}



