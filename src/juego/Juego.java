/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import juego.negocio.JuegoCompartido;
import juego.pantallas.PantallaJuego;
import juego.pantallas.PantallaLogin;

/**
 *
 * @author Marcelo
 */
public class Juego {
    
    public static PantallaLogin pantallaLogin;
    public static PantallaJuego pantallaJuego;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        pantallaLogin = new PantallaLogin();
        pantallaJuego = new PantallaJuego();
        
        pantallaLogin.setVisible(true);
    }
    
}
