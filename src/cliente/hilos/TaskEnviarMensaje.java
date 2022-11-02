/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.hilos;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo
 */
public class TaskEnviarMensaje extends Thread {
    
    private DataOutputStream flujosalida;
    private String mensaje;
    
    public TaskEnviarMensaje(DataOutputStream flujosalida, String mensaje) {
        this.flujosalida = flujosalida;
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        try {
            flujosalida.writeUTF(mensaje);
            
            System.out.println("\tMensaje enviado al Servidor > " + mensaje);
            //se puede despachar un mensaje enviado exitosamente
        } catch (IOException ex) {
            Logger.getLogger(TaskEnviarMensaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
