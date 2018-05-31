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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author william
 */
public class ServletGeneros extends HttpServlet {

    private Usuarios usuarios;

    public ServletGeneros() {
        usuarios = new Usuarios();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (usuarios == null) {
            usuarios = new Usuarios();
        }

        String id = request.getParameter("IdUsuario").trim();
        Usuario u = this.usuarios.FindById(id);
        ArrayList<String> gen = new ArrayList<>();
        if (u.getGenerosJuego() == null) {
            u.setGenerosJuego("");
            gen.add("sin generos");
        } else {
            String[] arreglo = u.getGenerosJuego().split(",");
            for (int i = 0; i < arreglo.length; i++) {
                gen.add(arreglo[i]);
            }
        }

        String json = new Gson().toJson(gen);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (usuarios == null) {
            usuarios = new Usuarios();
        }
        String r = "";
        String id = request.getParameter("IdUsuario").trim();
        String GenerosJuego = request.getParameter("GeneroJuego").trim();
        Usuario u = this.usuarios.FindById(id);
        boolean rep = false;
        String a = "";
        if (u.getGenerosJuego() != null) {
            String[] arreglo = u.getGenerosJuego().split(",");
            for (int i = 0; i < arreglo.length; i++) {
                if (GenerosJuego.equals(arreglo[i])) {
                    rep = true;
                    r = "ya agrego este genero";
                    break;
                }
            }
            if (!rep) {
                a = u.getGenerosJuego() + "," + GenerosJuego;
            }
        } else {
            a = GenerosJuego;
        }
        if (!rep) {
            u.setGenerosJuego(a);
            if (usuarios.update(u)) {
                r = "realizado";
            } else {
                r = "error al actualizar datos";
            }
        }
        response.setContentType("html/text");
        response.getWriter().write(r);
    }

}
