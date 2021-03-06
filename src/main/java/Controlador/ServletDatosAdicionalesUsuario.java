/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DB.Usuarios;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author willy
 */
@WebServlet(name = "ServletDatosAdicionalesUsuario", urlPatterns = {"/ServletDatosAdicionalesUsuario"})
public class ServletDatosAdicionalesUsuario extends HttpServlet {

    private Usuarios usu;

    public ServletDatosAdicionalesUsuario() throws URISyntaxException {
        this.usu = new Usuarios();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(this.usu ==null){
            this.usu = new Usuarios();
        }
        String r = "";
        HttpSession respuesta = request.getSession();
        String email = (String) respuesta.getAttribute("sessionEmail");
        String[] fecha = request.getParameter("FechaNacimiento").split("/");
        Date FechaNacimiento=null;
        String ExperienciaVideojuegos = request.getParameter("ExperienciaVideojuegos");
        String HorasPromedioJuego = request.getParameter("HorasPromedioJuego");
        String PlataformasVideojuegos = request.getParameter("PlataformasVideojuegos");
        String Idiomas = request.getParameter("Idiomas");
        String DiasJuego = request.getParameter("DiasJuego");
        String HorarioJuego = request.getParameter("HorarioJuego");
        String GenerosJuego = request.getParameter("GenerosJuego");
        Usuario u = this.usu.buscar2(email);
        try{
        int year = Integer.parseInt(fecha[2])-1900;
        int month = Integer.parseInt(fecha[1]);
        int day = Integer.parseInt(fecha[0]);
        FechaNacimiento = new Date(year,month,day);
        u.setFechaNacimiento(FechaNacimiento);
        }catch(Exception ex){
            
        }
        u.setExperienciaVideojuegos(ExperienciaVideojuegos);
        u.setHorasPromedioJuego(HorasPromedioJuego);
        u.setPlataformasVideojuegos(PlataformasVideojuegos);
        u.setIdiomas(Idiomas);
        u.setHorarioJuego(HorarioJuego);
        u.setGenerosJuego(GenerosJuego);
        u.setDiasJuego(DiasJuego);
        if (usu.update(u)) {
            r = "realizado";
        } else {
            r = "error al actualizar datos";
        }

        response.setContentType("html/text");
        response.getWriter().write(r);
    }

}
