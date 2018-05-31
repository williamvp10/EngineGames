/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DB.CalificacionComentarios;
import Modelo.CalificacionComentario;
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
@WebServlet(name = "ServletCalificacionComentario", urlPatterns = {"/ServletCalificacionComentario"})
public class ServletCalificacionComentario extends HttpServlet {

    private CalificacionComentarios ccomentarios;

    public ServletCalificacionComentario() {
        this.ccomentarios = new CalificacionComentarios();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           this.ccomentarios = new CalificacionComentarios();      
        //retornar por juego
        String id = request.getParameter("IdComentario").trim();
        ArrayList<String> c = new ArrayList<String>();
        int likes=this.ccomentarios.Likes(id);
        int nolikes=this.ccomentarios.NoLikes(id);
        c.add(""+likes);
        c.add(""+nolikes);

        String json = new Gson().toJson(c);
        response.setContentType("application/json");
        response.getWriter().write(json);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           this.ccomentarios = new CalificacionComentarios();    
        String r = "";
        //añadir Calificacion de un Comentario
        String nickname = request.getParameter("NickName").trim();
        String id = request.getParameter("IdComentario").trim();
        int cal = Integer.parseInt(request.getParameter("Calificacion").trim());
        CalificacionComentario c = new CalificacionComentario(nickname, id, cal);
        if (this.ccomentarios.find(nickname, id).size()<=0) {
            if (this.ccomentarios.insert(c)) {
                r = "inserto correctamente";
            } else {
                r = "null";
            }
        } else {
            if(this.ccomentarios.find(nickname, id).get(0).getCalificacion()== cal){
                this.ccomentarios.delete(c);
            }else {
                this.ccomentarios.update(c);
            }
        }
        response.setContentType("html/text");
        response.getWriter().write(r);
    }
}
