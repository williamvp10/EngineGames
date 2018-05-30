/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DB.Usuarios;
import Modelo.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "ServletUsuario", urlPatterns = {"/ServletUsuario"})
public class ServletUsuario extends HttpServlet {

    private Usuarios usuarios;

    public ServletUsuario() {
        usuarios = new Usuarios();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(usuarios==null){
            usuarios= new Usuarios();
        }
        //retornar info de un juego en especifico 
        String id = request.getParameter("IdUsuario").trim();
        ArrayList<Usuario> j = new ArrayList<Usuario>();
        j.add(this.usuarios.FindById(id));
        for (int i = 0; i < j.size(); i++) {
            if(j.get(i).getPais()==null  ){
                j.get(i).setPais("");
            }
            if(j.get(i).getDiasJuego()==null){
                j.get(i).setDiasJuego("");
            }
            if(j.get(i).getExperienciaVideojuegos()==null){
                j.get(i).setExperienciaVideojuegos("");
            }
            if(j.get(i).getGenerosJuego()==null){
                j.get(i).setGenerosJuego("");
            }
            if(j.get(i).getHorarioJuego()==null){
                j.get(i).setHorarioJuego("");
            }
            if(j.get(i).getIdiomas()==null){
                j.get(i).setIdiomas("");
            }
            if(j.get(i).getHorasPromedioJuego()==null){
                j.get(i).setHorasPromedioJuego("");
            }
        }
        String json = new Gson().toJson(j);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(usuarios==null){
            usuarios= new Usuarios();
        }
        String r="";
        String id = request.getParameter("IdUsuario").trim();
        String nombre = request.getParameter("Nombre").trim();
        Date FechaNacimiento = Date.valueOf(request.getParameter("FechaNacimiento"));
        String ExperienciaVideojuegos = request.getParameter("ExperienciaVideojuegos").trim();
        String HorasPromedioJuego = request.getParameter("HorasPromedioJuego").trim();
        String PlataformasVideojuegos = request.getParameter("PlataformasVideojuegos").trim();
        String Idiomas = request.getParameter("Idiomas").trim();
        String DiasJuego = request.getParameter("DiasJuego").trim();
        String HorarioJuego = request.getParameter("HorarioJuego").trim();
        String GenerosJuego = request.getParameter("GenerosJuego").trim();
        Usuario u = this.usuarios.FindById(id);
        u.setNombre(nombre);
        u.setFechaNacimiento(FechaNacimiento);
        u.setExperienciaVideojuegos(ExperienciaVideojuegos);
        u.setHorasPromedioJuego(HorasPromedioJuego);
        u.setPlataformasVideojuegos(PlataformasVideojuegos);
        u.setIdiomas(Idiomas);
        u.setHorarioJuego(HorarioJuego);
        u.setGenerosJuego(GenerosJuego);
        u.setDiasJuego(DiasJuego);
        if (usuarios.update(u)) {
            r = "realizado";
        } else {
            r = "error al actualizar datos";
        }

        response.setContentType("html/text");
        response.getWriter().write(r);
    }
}
