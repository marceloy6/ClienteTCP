/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.escuchadores;

import cliente.eventos.EventoCliente;
import java.util.EventListener;

/**
 *
 * @author Marcelo
 */
public interface EscuchadorConexion extends EventListener {
    
    public void OnConectado(EventoCliente eventoCliente);
    
    public void OnDesconectado(EventoCliente eventoCliente);
    
}
