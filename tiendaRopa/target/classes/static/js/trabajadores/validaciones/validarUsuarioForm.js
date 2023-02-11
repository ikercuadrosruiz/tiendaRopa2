'use strict'
window.addEventListener('load', function () {

    let inputs = document.querySelectorAll('input');

    inputs.forEach(i => {
        i.addEventListener('blur', validarCampo);
        i.addEventListener('keyup', validarCampo);
    });

    /*
    let btn = document.querySelector('form button');
    btn.addEventListener('click', function(e) {
        if (campos.correo && campos.nombre && campos.fechaNacimiento
            && campos.primerAp && campos.segundoAp && campos.direccion 
            && campos.poblacion && campos.cp) {
            
        }
    });
    */

});

// Validaciones -------------------------------------
const expresiones = {
    correo: /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/,
    nombre: /^(?=.{3,15}$)[a-zñáéíóú]+(?: [a-zñáéíóú]+)?$/, // Una mayuscula, una min y un numero mínimo
    cp: /^(?:0[1-9]|[1-4]\d|5[0-2])\d{3}$/
};

const campos = {
    correo: false,
    nombre: false,
    fechaNacimiento: false,
    primerAp: false,
    segundoAp: false,
    direccion: false,
    poblacion: false,
    cp: false
};

const validarCampo = (event) => {
    switch (event.target.name) {
        case "correo":
            if (expresiones.correo.test(event.target.value)) {
                document.getElementById('errCorreo').style.display = 'none';
                campos['correo'] = true;
            } else {
                document.getElementById('errCorreo').textContent = "Correo electrónico mal formado";
                document.getElementById('errCorreo').style.display = 'block';
                campos['correo'] = false;
            }
            break;

        case "nombre":
            if (expresiones.nombre.test(event.target.value)) {
                campos['nombre'] = true;
                document.getElementById('errNombre').style.display = 'none';
            } else {
                campos['nombre'] = false;
                document.getElementById('errNombre').textContent = "Nombre no válido";
                document.getElementById('errNombre').style.display = 'block';
            }
            break;

        case "fecha":
            if (event.target.value == "") {
                campos['fechaNacimiento'] = false;
                document.getElementById('errFecha').textContent = "Rellene el campo fecha";
                document.getElementById('errFecha').style.display = 'block';

            } else if (new Date(event.target.value).getFullYear() > new Date().getFullYear() || event.target.value.length > 10) {
                campos['fechaNacimiento'] = false;
                document.getElementById('errFecha').textContent = "El año no puede ser mayor que el año actual";
                document.getElementById('errFecha').style.display = 'block';

            } else {
                campos['fechaNacimiento'] = true;
                document.getElementById('errFecha').style.display = 'none';
            }


            break;

        case "ap1":
            if (expresiones.nombre.test(event.target.value)) {
                campos['primerAp'] = true;
                document.getElementById('errAp1').style.display = 'none';
            } else {
                campos['primerAp'] = false;
                document.getElementById('errAp1').textContent = "Apellido no válido";
                document.getElementById('errAp1').style.display = 'block';
            }
            break;

        case "ap2":
            if (expresiones.nombre.test(event.target.value)) {
                campos['segundoAp'] = true;
                document.getElementById('errAp2').style.display = 'none';
            } else {
                campos['segundoAp'] = false;
                document.getElementById('errAp2').textContent = "Apellido no válido";
                document.getElementById('errAp2').style.display = 'block';
            }
            break;

        case "direccion":
            if (event.target.value.replace(/ /g,'') != "") {
                campos['direccion'] = true;
                document.getElementById('errDireccion').style.display = 'none';
            } else {
                campos['direccion'] = false;
                document.getElementById('errDireccion').textContent = "Campo dirección vacio";
                document.getElementById('errDireccion').style.display = 'block';
            }
            break;

        case "poblacion":
            if (event.target.value.replace(/ /g,'') != "") {
                campos['poblacion'] = true;
                document.getElementById('errPoblacion').style.display = 'none';
            } else {
                campos['poblacion'] = false;
                document.getElementById('errPoblacion').textContent = "Campo población vacio";
                document.getElementById('errPoblacion').style.display = 'block';
            }
            break;

        case "cp":
            if (expresiones.cp.test(event.target.value)) {
                campos['cp'] = true;
                document.getElementById('errCp').style.display = 'none';
            } else {
                campos['cp'] = false;
                document.getElementById('errCp').textContent = "Código Postal no válido. Ej: 46520";
                document.getElementById('errCp').style.display = 'block';
            }
            break;

        default:
            break;
    }
};