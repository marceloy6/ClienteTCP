/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.negocio;

import cliente.eventos.Cliente;

/**
 *
 * @author Marcelo
 */
public class Jugador {
    
    private long id;
    private String nombre;
    private boolean conectado;
    private boolean turno;
    
    private final int dimf = 4;
    private final int dimc = 3;
    private final int[][] matriz = new int[dimf][dimc];
    private final int defaultValue = -1;
    
    private int cantGrandes = 0;
    private boolean ganador = false;
    
    public Jugador() {
        id = -1;
        nombre = "";
        conectado = false;
        turno = false;
        InicializarMatriz();
    }
    
    public Jugador(long id){
        this.id = id;
        nombre = "";
        conectado = false;
        turno = false;
        InicializarMatriz();
    }
    
    public Jugador(Cliente cliente) {
        this.id = cliente.getId();
        this.nombre = cliente.getUser();
        conectado = true;
        InicializarMatriz();
    }
    
    private void InicializarMatriz() {
        for (int i = 0; i < dimf; i++) {
            for (int j = 0; j < dimc; j++) {
                matriz[i][j] = defaultValue;
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }
    
    

    public int[][] getMatriz() {
        return matriz;
    }

    public int getDimf() {
        return dimf;
    }

    public int getDimc() {
        return dimc;
    }
    
    
    
    
    
    public void PonerBala(int puntos) {
        matriz[0][0] = puntos;
    }
    
    public void PonerTonto(int puntos) {
        matriz[1][0] = puntos;
    }
    
    public void PonerTren(int puntos) {
        matriz[2][0] = puntos;
    }
    
    public void PonerCuadra(int puntos) {
        matriz[0][2] = puntos;
    }
    
    public void PonerQuina(int puntos) {
        matriz[1][2] = puntos;
    }
    
    public void PonerSena(int puntos) {
        matriz[2][2] = puntos;
    }
    
    public void PonerEscalera(int puntos) {
        matriz[0][1] = puntos;
    }
    
    public void PonerFull(int puntos) {
        matriz[1][1] = puntos;
    }
    
    public void PonerPoker(int puntos) {
        matriz[2][1] = puntos;
    }
    
    public boolean PonerGrande(int puntos) {
        cantGrandes = cantGrandes + 1;
        boolean res = false;
        if (cantGrandes == 1) {
            matriz[3][0] = puntos;
            res = true;
        }
        if (cantGrandes == 2) {
            matriz[3][1] = puntos;
            res = true;
        }
        System.out.println("\t\tSe puso una Grande = " + matriz[3][0] + ", " + matriz[3][1]);
        return res;
    }
    
//    public void PonerGrande1(int puntos) {
//        matriz[3][0] = puntos;
//    }
//    
//    public void PonerGrande2(int puntos) {
//        matriz[3][1] = puntos;
//    }
    
    public boolean HayEspacioEnCasilla(String nombreCasilla){
        switch (nombreCasilla) {
            case Jugada.BALAS:
                return (matriz[0][0]==defaultValue);
            case Jugada.TONTOS:
                return (matriz[1][0]==defaultValue);
            case Jugada.TRENES:
                return (matriz[2][0]==defaultValue);
            case Jugada.CUADRAS:
                return (matriz[0][2]==defaultValue);
            case Jugada.QUINAS:
                return (matriz[1][2]==defaultValue);
            case Jugada.SENAS:
                return (matriz[2][2]==defaultValue);
            case Jugada.ESCALERA:
                return (matriz[0][1]==defaultValue);
            case Jugada.FULL:
                return (matriz[1][1]==defaultValue);
            case Jugada.POKER: 
                return (matriz[2][1]==defaultValue);
            case Jugada.GRANDE:
                return (matriz[3][0]==defaultValue || matriz[3][1]==defaultValue);
        }
        return false;
    }
    
    public int getValorCasilla(String nombreCasilla) {
        int res = defaultValue;
        switch (nombreCasilla) {
            case Jugada.BALAS:
                res = (matriz[0][0]);
                break;
            case Jugada.TONTOS:
                res = (matriz[1][0]);
                break;
            case Jugada.TRENES:
                res = (matriz[2][0]);
                break;
            case Jugada.CUADRAS:
                res = (matriz[0][2]);
                break;
            case Jugada.QUINAS:
                res = (matriz[1][2]);
                break;
            case Jugada.SENAS:
                res = (matriz[2][2]);
                break;
            case Jugada.ESCALERA:
                res = (matriz[0][1]);
                break;
            case Jugada.FULL:
                res = (matriz[1][1]);
                break;
            case Jugada.POKER: 
                res = (matriz[2][1]);
                break;
            case Jugada.GRANDE:
                if (matriz[3][0] != defaultValue) {
                    res = matriz[3][0];
                } else {
                    res = matriz[3][1];
                }
                break;
                
//            case Jugada.GRANDE1:
//                res = matriz[3][0];
//                break;
//            case Jugada.GRANDE2:
//                res = matriz[3][1];
//                break;
        }
        return res;
    }
    
    public void PonerJugada(String nombreJugada, int valorJugada) {
        switch (nombreJugada) {
            case Jugada.BALAS:
                matriz[0][0] = valorJugada;
                break;
            case Jugada.TONTOS:
                matriz[1][0] = valorJugada;
                break;
            case Jugada.TRENES:
                matriz[2][0] = valorJugada;
                break;
            case Jugada.CUADRAS:
                matriz[0][2] = valorJugada;
                break;
            case Jugada.QUINAS:
                matriz[1][2] = valorJugada;
                break;
            case Jugada.SENAS:
                matriz[2][2] = valorJugada;
                break;
            case Jugada.ESCALERA:
                matriz[0][1] = valorJugada;
                break;
            case Jugada.FULL:
                matriz[1][1] = valorJugada;
                break;
            case Jugada.POKER: 
                matriz[2][1] = valorJugada;
                break;
            case Jugada.GRANDE:
                PonerGrande(valorJugada);
                break;
        }
    }
    
    

    public boolean isGanador() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }

    public int getDefaultValue() {
        return defaultValue;
    }
    
    
    
    
}
