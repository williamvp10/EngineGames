/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author william
 */
public class CalificacionComentario {
    private String Nickname;
    private String IdComentario;
    private int Calificacion;

    public CalificacionComentario() {
    }

    public CalificacionComentario(String Nickname, String IdComentario, int Calificacion) {
        this.Nickname = Nickname;
        this.IdComentario = IdComentario;
        this.Calificacion = Calificacion;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String Nickname) {
        this.Nickname = Nickname;
    }

    public String getIdComentario() {
        return IdComentario;
    }

    public void setIdComentario(String IdComentario) {
        this.IdComentario = IdComentario;
    }

    public int getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(int Calificacion) {
        this.Calificacion = Calificacion;
    }

    
}
