/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.eventos;

/**
 *
 * @author Marcelo
 */
public class Mensaje {
    
    private String mensajeServidor;
    private Cliente cliente;
    
    public Mensaje(String mensaje, Cliente cliente) {
        this.mensajeServidor = mensaje;
        this.cliente = cliente;
    }

    public String getMensajeServidor() {
        return mensajeServidor;
    }

    public void setMensajeServidor(String mensaje) {
        this.mensajeServidor = mensaje;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    
}
