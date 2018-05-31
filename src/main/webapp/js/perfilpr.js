
function actualizarPerfil() {
    infoPerfilpr();
    cuadrito();
}

function infoPerfilpr() {
    Limpiar();
    document.getElementById('InfoPerfil').style.display = 'block';
    var id = document.getElementById('nickname').value;
    var parametros = {
        "IdUsuario": id
    };
    $.ajax({
        data: parametros,
        url: "ServletUsuario",
        type: "GET"

    }).done(function (response) {
        var usuarios = response;
        document.getElementById('Nicknamepr').value = usuarios[0].Nickname;
        document.getElementById('Nombrepr').value = usuarios[0].Nombre;
        document.getElementById('Correopr').value = usuarios[0].Email;
        document.getElementById('Paispr').value = usuarios[0].Pais;
        document.getElementById('FechaNacimientopr').value = usuarios[0].FechaNacimiento;
        document.getElementById('Horaspr').value = usuarios[0].HorasPromedioJuego;
        document.getElementById('Diaspr').value = usuarios[0].DiasJuego;
        document.getElementById('Idiomapr').value = usuarios[0].Idiomas;
        document.getElementById('Acercapr').value = usuarios[0].ExperienciaVideojuegos;
    });
    getGeneros();

}
;
function cuadrito() {
    var id = document.getElementById('nickname').value;
    var parametros = {
        "IdUsuario": id
    };
    $.ajax({
        data: parametros,
        url: "ServletUsuario",
        type: "GET"

    }).done(function (response) {
        var usuarios = response;
        document.getElementById('nickc').innerHTML = usuarios[0].Nickname;
        document.getElementById('nombrec').innerHTML = usuarios[0].Nombre;
        document.getElementById('generosc').innerHTML = usuarios[0].GenerosJuego;
        document.getElementById('descripcionc').innerHTML = usuarios[0].ExperienciaVideojuegos;
    });


}
;


function GuardarCambiosPerfil() {
    var parametros = {
        "IdUsuario": document.getElementById('Nicknamepr').value,
        "Nombre": document.getElementById('Nombrepr').value,
        "FechaNacimiento": document.getElementById('FechaNacimientopr').value,
        "ExperienciaVideojuegos": document.getElementById('Acercapr').value,
        "HorasPromedioJuego": document.getElementById('Horaspr').value,
        "PlataformasVideojuegos": "pc",
        "Idiomas": document.getElementById('Idiomapr').value,
        "DiasJuego": document.getElementById('Diaspr').value,
        "HorarioJuego": document.getElementById('Horaspr').value
    };
    $.ajax({
        data: parametros,
        url: "ServletDatosAdicionalesUsuario",
        type: "POST"

    }).done(function (response) {
        bluee(response);
        actualizarPerfil();
    });
}
;

function addGeneros() {
    var nickname = $("#nickname").val();
    var gen = $("#addgenero").val();
    var parametros = {
        "IdUsuario": nickname,
        "GeneroJuego": gen
    };
    $.ajax({
        data: parametros,
        url: "ServletGeneros",
        type: "POST"

    }).done(function (response) {
        var gen = response;
        bluee(response);
    });
    actualizarPerfil();
}
;

function getGeneros() {
    var nickname = $("#nickname").val();
    document.getElementById('Generos').innerHTML = '';
    var parametros = {
        "IdUsuario": nickname
    };
    $.ajax({
        data: parametros,
        url: "ServletGeneros",
        type: "GET"

    }).done(function (response) {
        var gen = response;
        for (var i in gen) {
            $("#Generos").append(
                    ' <li class="tag"><a>' + gen[i] + '</a></li>');
        }
    });
}
