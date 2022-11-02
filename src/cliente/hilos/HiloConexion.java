/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.hilos;

import cliente.Despachador;
import cliente.eventos.EventoCliente;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo
 */
public class HiloConexion extends Thread {
    
    private EventoCliente eventoCliente;
    private boolean sw;
    
    public HiloConexion(EventoCliente eventoCliente) {
        this.eventoCliente = eventoCliente;
        sw = true;
    }

    @Override
    public void run() {
        while (sw) {
            try {
                System.out.println("Conectando al servidor...");
                Socket socket = new Socket(eventoCliente.getCliente().getHost(), eventoCliente.getCliente().getPuerto());
                eventoCliente.getCliente().setSocketCliente(socket);
                
                //notificar de la conexion
                sw = false;
                System.out.println("Conectado! Despachando a los escuchadores...");
                Despachador.DespacharEventoConexion(eventoCliente);
            } catch (IOException ex) {
                System.out.println("Servidor No Encontrado !!");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex1) {
                    Logger.getLogger(HiloConexion.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            
        }
    }
    
    public void Detener() {
        sw = false;
    }
    
}
