/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.eventos;

import java.util.EventObject;

/**
 *
 * @author Marcelo
 */
public class EventoCliente extends EventObject {
    
    private Cliente cliente;
    private boolean detenido;
    
    public EventoCliente(Object source, Cliente cliente) {
        super(source);
        this.cliente = cliente;
        detenido = false;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean estaDetenido() {
        return detenido;
    }

    public void Detener() {
        detenido = true;
        this.cliente.Desconectar();
    }
    
    
    
}
