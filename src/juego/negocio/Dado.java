/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.negocio;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Marcelo
 */
public class Dado {
    
    //private int[][] caras = {{1,6},{2,5},{3,4}};
    private int valor;
    private boolean marcado = false;
    private boolean seleccionado;
    
    private byte cantVolteos;
    private byte cantLanzamientos;
    
    public Dado() {
        valor = ThreadLocalRandom.current().nextInt(1, 6+1);
        seleccionado = true;
        
        cantVolteos = 0;
        cantLanzamientos = 0;
    }
    
    public int Lanzar() {
        valor = ThreadLocalRandom.current().nextInt(1, 6+1);
        cantLanzamientos = (byte) (cantLanzamientos + 1);
        return valor;
    }
    
    public int Voltear() {
        cantVolteos = (byte) (cantVolteos + 1);
        valor = 7 - valor;
        return valor;
    }
    
    public void Reset() {
        valor = 0;
        cantVolteos = 0;
        seleccionado = true;
    }
    
    
    
    
    public void setValor( int valor ) {
        this.valor = valor;
    }
    
    public int getValor() {
        return valor;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public byte getCantVolteos() {
        return cantVolteos;
    }

    public void setCantVolteos(byte cantVolteos) {
        this.cantVolteos = cantVolteos;
    }

    public byte getCantLanzamientos() {
        return cantLanzamientos;
    }

    public void setCantLanzamientos(byte cantLanzamientos) {
        this.cantLanzamientos = cantLanzamientos;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }
    
    
    
}
