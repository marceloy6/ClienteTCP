/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.dibujo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import juego.negocio.Jugador;

/**
 *
 * @author Marcelo
 */
public class Dibujador {
    
    private Graphics lienzo;
    private final int x, y;
    private final int espacioEntreTablas;
    private final int interlineado;
    
    private final int dimcasilla;
    private final int dimTableroAncho;
    private final int dimTableroAlto;
    private final int fondo_ancho, fondo_alto;
    
    private Jugador jugador;
    
    public Dibujador(Graphics g, Jugador jugador, int fondo_ancho, int fondo_alto) {
        lienzo = g;
        lienzo.setColor(Color.black);
        
        this.jugador = jugador;
        
        x = 10;
        y = 10;
        this.dimcasilla = 26;
        
        this.fondo_ancho = fondo_ancho;
        this.fondo_alto = fondo_alto;
        
        dimTableroAncho = dimcasilla * jugador.getDimc();
        dimTableroAlto = dimcasilla * jugador.getDimf();
        espacioEntreTablas = 20;
        
        interlineado = 10;
    }
    
    public void DibujarTablero(int x, int y, Jugador jugador) {
        
        int dimf = jugador.getDimf();
        int dimc = jugador.getDimc();
        int xtemp = x;
        int ytemp = y;
        for (int i = 0; i < dimf - 1; i++) {
            for (int j = 0; j < dimc; j++) {
                lienzo.drawRect(xtemp, ytemp, dimcasilla, dimcasilla);
                
                if (jugador.getMatriz()[i][j] != jugador.getDefaultValue()) {
                    String valor = String.valueOf(jugador.getMatriz()[i][j]);
                    
                    int posTextX = xtemp + Math.round(dimcasilla * 0.3f);
                    int posTextY = ytemp + Math.round(dimcasilla * 0.7f);
                    lienzo.drawString(valor, posTextX, posTextY);
                }
                
                xtemp = xtemp + dimcasilla;
            }
            xtemp = x;
            ytemp = ytemp + dimcasilla;
        }
        
        //-------dibujando la ultima fila----------------------
        int i = dimf - 1;
        xtemp = xtemp + dimcasilla/2;
        for (int j = 0; j < dimc - 1; j++) {
            lienzo.drawRect(xtemp, ytemp, dimcasilla, dimcasilla);

            if (jugador.getMatriz()[i][j] != jugador.getDefaultValue()) {
                String valor = String.valueOf(jugador.getMatriz()[i][j]);

                int posTextX = xtemp + Math.round(dimcasilla * 0.3f);
                int posTextY = ytemp + Math.round(dimcasilla * 0.7f);
                lienzo.drawString(valor, posTextX, posTextY);
            }
            
            xtemp = xtemp + dimcasilla;
        }
        xtemp = x;
        ytemp = ytemp + dimcasilla;
        //----------------------------------------------------------------------

        ytemp = ytemp + interlineado*2;
        String turno = jugador.isTurno() + "";
        lienzo.drawString(jugador.getNombre() + "\u3010" + turno.toUpperCase() + "\u3011", xtemp, ytemp);
        
        Color color = lienzo.getColor();
        lienzo.setColor(Color.green);
        if (!jugador.isConectado()) {
            lienzo.setColor(Color.red);
        }
        ytemp = ytemp + interlineado;
        lienzo.fillOval(xtemp, ytemp, 13, 13);
        
        lienzo.setColor(color);
    }
    
    public void DibujarOtroJugadores(List<Jugador> lista) {
        
        int x = this.x;
        int y = this.y;
        
        for (Jugador jugadorTemp : lista) {
            if (jugador.getId() != jugadorTemp.getId()) {
                DibujarTablero(x, y, jugadorTemp);
                x = x + dimTableroAncho + espacioEntreTablas;
                
                if (x > fondo_ancho - dimTableroAncho) {
                    x = this.x;
                    y = y + dimTableroAlto + espacioEntreTablas + interlineado*3;
                }
            }
        }
    }
    
    public void DibujarJugadorLocal() {
        int x = fondo_ancho/2 - dimTableroAncho/2;
        int y = fondo_alto - dimTableroAlto - dimTableroAlto/2;
        
        DibujarTablero(x, y, jugador);
    }
    
    public synchronized void Dibujar(List<Jugador> lista) {
        System.out.println("---> Dibujador(List) INICIO !!");
        
        LimpiarPantalla();
        DibujarOtroJugadores(lista);
        DibujarJugadorLocal();
        
        System.out.println("---> Dibujador(List) FIN !!");
    }
    
    public void LimpiarPantalla() {
        Color color = lienzo.getColor();
        
        lienzo.setColor(Color.white);
        lienzo.fillRect(0, 0, fondo_ancho, fondo_alto);
        
        lienzo.setColor(color);
    }
    
    

    public Graphics getLienzo() {
        return lienzo;
    }

    public void setLienzo(Graphics lienzo) {
        this.lienzo = lienzo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
}
