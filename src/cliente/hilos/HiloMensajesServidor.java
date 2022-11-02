/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.hilos;

import cliente.Despachador;
import cliente.eventos.Cliente;
import cliente.eventos.EventoCliente;
import cliente.eventos.EventoMensaje;
import cliente.eventos.Mensaje;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo
 */
public class HiloMensajesServidor extends Thread {
    
    private DataInputStream flujoentrada;
    private EventoCliente eventoCliente;
    private Cliente cliente;
    private boolean sw;
    
    public HiloMensajesServidor(EventoCliente eventoCliente) {
        this.eventoCliente = eventoCliente;
        this.flujoentrada = eventoCliente.getCliente().getFlujoentrada();
        this.cliente = eventoCliente.getCliente();
        sw = true;
    }

    @Override
    public void run() {
        while (sw) {            
            try {
                System.out.println("Escuchando Mensajes del Servidor!!");
                String mensajeServidor = flujoentrada.readUTF();
                
                //notificar del nuevo mensaje recibido
                Despachador.DespacharEventoMensajeRecibido(new EventoMensaje(this, new Mensaje(mensajeServidor, cliente)));
            } catch (IOException ex) {
                //Logger.getLogger(HiloMensajesServidor.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error de Conexion!! " + ex.getMessage());
                sw = false;
            }
        }
        
        //notificamos de la desconexion
        if (!eventoCliente.estaDetenido()) {
            Despachador.DespacharEventoDesconexion(eventoCliente);
        }
    }
    
    public void Detener() {
        sw = false;
    }
    
    
}
