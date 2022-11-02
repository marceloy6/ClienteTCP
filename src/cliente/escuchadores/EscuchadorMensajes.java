/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.escuchadores;

import cliente.eventos.EventoMensaje;
import java.util.EventListener;

/**
 *
 * @author Marcelo
 */
public interface EscuchadorMensajes extends EventListener {
    
    public void OnMensajeRecibido(EventoMensaje eventoMensaje);
    
    
    
    public void OnIdentificado(EventoMensaje eventoMensaje);
    
    public void OnLoginOK(EventoMensaje eventoMensaje);
    
    public void OnRegistroOK(EventoMensaje eventoMensaje);
    
    public void OnComerzarPartidaOK(EventoMensaje eventoMensaje);
    
    public void OnGastarTurnoOK(EventoMensaje eventoMensaje);
    
    
    
    public void OnUnJugadorDesconectado(EventoMensaje eventoMensaje);
    
    public void OnUnJugadorConectado(EventoMensaje eventoMensaje);
    
    
    public void OnHayPartidaEnCurso(EventoMensaje eventoMensaje);
    
}
