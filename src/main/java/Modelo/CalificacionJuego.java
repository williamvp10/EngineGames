/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Oscar
 */
public class CalificacionJuego {

    private String Nickname;
    private String IdJuego;
    private int Calificacion;

    public CalificacionJuego() {
    }

    public CalificacionJuego(String Nickname, String IdJuego, int Calificacion) {
        this.Nickname = Nickname;
        this.IdJuego = IdJuego;
        this.Calificacion = Calificacion;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String Nickname) {
        this.Nickname = Nickname;
    }

    public String getIdJuego() {
        return IdJuego;
    }

    public void setIdJuego(String IdJuego) {
        this.IdJuego = IdJuego;
    }

    public int getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(int Calificacion) {
        this.Calificacion = Calificacion;
    }
}
