function cambioColor(cambio) {
    var pass = document.getElementById('passNueva');
    if (pass.value !== cambio) {
        document.getElementById('mensaje').innerHTML = "Las Contraseñas No Coinciden";
        document.cambiarPass.passNuevaRepetir.style.background = '#d9534f';
    } else {
        document.getElementById('mensaje').innerHTML = "Las Contraseña Coinciden";
        document.cambiarPass.passNuevaRepetir.style.background = '#5cb85c';
    }
}