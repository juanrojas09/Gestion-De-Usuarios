$(document).ready(function() {
    // registrarUsuarios();




});

async function iniciarSesion() {
    let datos={};
    datos.mail=document.getElementById('txtEmail').value;
    datos.pass= document.getElementById('txtPassword').value;


    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)

    });
    const respuesta = await request.text();

   if(respuesta!="fail"){
       localStorage.token=respuesta;
       localStorage.mail=datos.mail;
       window.location.href="usuarios.html",true;
       alert("intente de nuevo");
   }
}