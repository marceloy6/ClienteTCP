/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.negocio;

import cliente.ClienteSocket;
import java.util.ArrayList;

/**
 *
 * @author Marcelo
 */
public class JuegoCompartido {
    
    public static ClienteSocket clienteSocket = null;
    public static ArrayList<Jugador> listaJugadores = new ArrayList<>();
    
    public static void Desconectar() {
        if(listaJugadores != null) {
            for (Jugador jugador : listaJugadores) {
                jugador.setConectado(false);
            }
        }
    }
    
    //si existe lo actualiza, sino lo agrega
    public static void ActualizarAgregarJugadorEnLista(Jugador j) {
        boolean encontrado = false;
        for (Jugador jugador : listaJugadores) {
            if (jugador.getId() == j.getId()) {
                encontrado = true;
                jugador.setConectado(j.isConectado());
                jugador.setTurno(j.isTurno());
                break;
            }
        }
        if (!encontrado) {
            listaJugadores.add(j);
        }
    }
    
    //solo actualiza si lo encuentra
    public static void ActualizarJugadorEnLista(Jugador j) {
        for (Jugador jugador : listaJugadores) {
            if (jugador.getId() == j.getId()) {
                jugador.setConectado(j.isConectado());
                jugador.setTurno(j.isTurno());
                jugador.setGanador(j.isGanador());
                break;
            }
        }
    }
    
    public static void ActualizarJugadorEnLista(Jugador j, String nombreJugada, int valorJugada) {
        for (Jugador jugador : listaJugadores) {
            if (jugador.getId() == j.getId()) {
                jugador.setConectado(j.isConectado());
                jugador.setTurno(j.isTurno());
                jugador.setGanador(j.isGanador());
                
                jugador.PonerJugada(nombreJugada, valorJugada);
                break;
            }
        }
    }
    
    
    public static Jugador ObtenerGanador() {
        Jugador jres = null;
        for (Jugador jugador : listaJugadores) {
            if (jugador.isGanador()) {
                jres = jugador;
            }
        }
        return jres;
    }
    
}
