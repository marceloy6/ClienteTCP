/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import cliente.escuchadores.EscuchadorConexion;
import cliente.escuchadores.EscuchadorMensajes;
import cliente.eventos.EventoCliente;
import cliente.eventos.EventoMensaje;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Marcelo
 */
public class Despachador {
    
    public static EventListenerList listaEscuchadores = new EventListenerList();
    
    public static void DespacharEventoConexion(EventoCliente eventoCliente) {
        EscuchadorConexion[] escuchadores = listaEscuchadores.getListeners(EscuchadorConexion.class);
        for (EscuchadorConexion escuchador : escuchadores) {
            escuchador.OnConectado(eventoCliente);
        }
    }
    
    public static void DespacharEventoDesconexion(EventoCliente eventoCliente) {
        EscuchadorConexion[] escuchadores = listaEscuchadores.getListeners(EscuchadorConexion.class);
        for (EscuchadorConexion escuchador : escuchadores) {
            escuchador.OnDesconectado(eventoCliente);
        }
    }
    
    
    
    
    
    
    
    
    public static void DespacharEventoMensajeRecibido(EventoMensaje eventoMensaje) {
        EscuchadorMensajes[] escuchadores = listaEscuchadores.getListeners(EscuchadorMensajes.class);
        for (EscuchadorMensajes escuchador : escuchadores) {
            escuchador.OnMensajeRecibido(eventoMensaje);
        }
    }
    
    
    
    
    
    
    
    
    public static void DespacharEventoIdentificado(EventoMensaje eventoMensaje) {
        EscuchadorMensajes[] escuchadores = listaEscuchadores.getListeners(EscuchadorMensajes.class);
        for (EscuchadorMensajes escuchador : escuchadores) {
            escuchador.OnIdentificado(eventoMensaje);
        }
    }
    
    public static void DespacharEventoLoginOK(EventoMensaje eventoMensaje) {
        EscuchadorMensajes[] escuchadores = listaEscuchadores.getListeners(EscuchadorMensajes.class);
        for (EscuchadorMensajes escuchador : escuchadores) {
            escuchador.OnLoginOK(eventoMensaje);
        }
    }
    
    public static void DespacharEventoRegistroOK(EventoMensaje eventoMensaje) {
        EscuchadorMensajes[] escuchadores = listaEscuchadores.getListeners(EscuchadorMensajes.class);
        for (EscuchadorMensajes escuchador : escuchadores) {
            escuchador.OnRegistroOK(eventoMensaje);
        }
    }
    
    public static void DespacharEventoComerzarPartidaOK(EventoMensaje eventoMensaje) {
        EscuchadorMensajes[] escuchadores = listaEscuchadores.getListeners(EscuchadorMensajes.class);
        for (EscuchadorMensajes escuchador : escuchadores) {
            escuchador.OnComerzarPartidaOK(eventoMensaje);
        }
    }
    
    
    
    
    
    public static void DespacharEventoUnJugadorSeDesconecto(EventoMensaje eventoMensaje) {
        EscuchadorMensajes[] escuchadores = listaEscuchadores.getListeners(EscuchadorMensajes.class);
        for (EscuchadorMensajes escuchador : escuchadores) {
            escuchador.OnUnJugadorDesconectado(eventoMensaje);
        }
    }
    
    public static void DespacharEventoUnJugadorSeConecto(EventoMensaje eventoMensaje) {
        EscuchadorMensajes[] escuchadores = listaEscuchadores.getListeners(EscuchadorMensajes.class);
        for (EscuchadorMensajes escuchador : escuchadores) {
            escuchador.OnUnJugadorConectado(eventoMensaje);
        }
    }
    
    public static void DespacharEventoGastarTurnoOK(EventoMensaje eventoMensaje) {
        EscuchadorMensajes[] escuchadores = listaEscuchadores.getListeners(EscuchadorMensajes.class);
        for (EscuchadorMensajes escuchador : escuchadores) {
            escuchador.OnGastarTurnoOK(eventoMensaje);
        }
    }
    
    
    public static void DespacharEventoHayPartidaEnCurso(EventoMensaje eventoMensaje) {
        EscuchadorMensajes[] escuchadores = listaEscuchadores.getListeners(EscuchadorMensajes.class);
        for (EscuchadorMensajes escuchador : escuchadores) {
            escuchador.OnHayPartidaEnCurso(eventoMensaje);
        }
    }
}
