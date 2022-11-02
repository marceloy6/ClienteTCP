/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.negocio;

import java.util.ArrayList;

/**
 *
 * @author Marcelo
 */
public class Cubilete {
    
    private Dado[] dados;
    private final byte nroDados = 5;
    private byte lanzamientos;
    private byte volteos;
    
    public Cubilete() {
        lanzamientos = 2;
        volteos = 2;
        dados = new Dado[nroDados];
        for (int i = 0; i < dados.length; i++) {
            dados[i] = new Dado();
        }
    }
    
    public boolean LanzarCubilete() {
        boolean res = false;
        if (lanzamientos > 0) {
            for (Dado dado : dados) {
                if (dado.isSeleccionado()) {
                    dado.Lanzar();
                }
            }
            lanzamientos = (byte) (lanzamientos - 1);
            res = true;
        }
        return res;
    }
    
    public Dado getDado(int index) {
        return dados[index];
    }
    
    public boolean VoltearDado(int index) {
        boolean res = false;
        if (volteos > 0) {
            dados[index].Voltear();
            volteos = (byte) (volteos - 1);
            res = true;
        }
        return res;
    }
    
    public int cantidadDadosSeleccionados() {
        int cont = 0;
        for (Dado dado : dados) {
            if (dado.isSeleccionado()) {
                cont++;
            }
        }
        return cont;
    }
    
    public void Reset() {
        lanzamientos = 2;
        volteos = 2;
        for (Dado dado : dados) {
            dado.Reset();
        }
    }
    
    public int ObtenerSumaDeLosDados() {
        int acu = 0;
        for (Dado dado : dados) {
            acu = acu + dado.getValor();
        }
        return acu;
    }
    
    public void ResetMarcadoDeDados() {
        for (Dado dado : dados) {
            dado.setMarcado(false);
        }
    }
    
    public int ObtenerSumaDeLosDadosConValor(int valor) {
        int acu = 0;
        for (Dado dado : dados) {
            if (dado.getValor() == valor) {
                acu = acu + valor;
            }
        }
        return acu;
    }
    
    
    

    public Dado[] getDados() {
        return dados;
    }

    public void setDados(Dado[] dados) {
        this.dados = dados;
    }

    public byte getLanzamientos() {
        return lanzamientos;
    }

    public void setLanzamientos(byte lanzamientos) {
        this.lanzamientos = lanzamientos;
    }

    public byte getNroDados() {
        return nroDados;
    }
    
    
    
}
