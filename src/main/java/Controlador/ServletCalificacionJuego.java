/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DB.CalificacionComentarios;
import DB.CalificacionJuegos;
import DB.Juegos;
import Modelo.CalificacionComentario;
import Modelo.CalificacionJuego;
import Modelo.Juego;
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
 * @author william
 */
@WebServlet(name = "ServletCalificacionJuego", urlPatterns = {"/ServletCalificacionJuego"})
public class ServletCalificacionJuego extends HttpServlet {

    private CalificacionJuegos cjuegos;
    private Juegos juegos;

    public ServletCalificacionJuego() {
        this.cjuegos = new CalificacionJuegos();
        this.juegos = new Juegos();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (this.cjuegos == null) {
            this.cjuegos = new CalificacionJuegos();
        }
        if (this.juegos == null) {
            this.juegos = new Juegos();
        }
        //retornar por juego
        String id = request.getParameter("IdJuego").trim();
        ArrayList<String> c = new ArrayList<String>();
        double num = this.cjuegos.Likes(id);
        c.add("" + num);

        String json = new Gson().toJson(c);
        response.setContentType("application/json");
        response.getWriter().write(json);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (this.cjuegos == null) {
            this.cjuegos = new CalificacionJuegos();
        }
        if (this.juegos == null) {
            this.juegos = new Juegos();
        }
        String r = "";
        //añadir Calificacion de un juego
        String nickname = request.getParameter("Nickname").trim();
        String id = request.getParameter("IdJuego").trim();
        int cal = Integer.parseInt(request.getParameter("Calificacion").trim());
        CalificacionJuego c = new CalificacionJuego(nickname, id, cal);

        if (this.cjuegos.find(nickname, id).size() <= 0) {
            if (this.cjuegos.insert(c)) {
                r = "inserto correctamente";
            } else {
                r = "null";
            }
        } else {
            if (this.cjuegos.find(nickname, id).get(0).getCalificacion() == cal) {
                this.cjuegos.delete(c);
            } else {
                this.cjuegos.update(c);
            }
        }
        Juego j = this.juegos.findbyId("" + id).get(0);

        j.setCalificacion("" + this.cjuegos.Likes(id));
        this.juegos.update(j);
        System.out.println("--------------------" + r);
        response.setContentType("html/text");
        response.getWriter().write(r);
    }
}
