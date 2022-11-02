/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.hilos;

import cliente.ClienteUtils;
import cliente.Despachador;
import cliente.eventos.Cliente;
import cliente.eventos.EventoMensaje;
import cliente.eventos.Mensaje;

/**
 *
 * @author Marcelo
 */
public class TaskLeerComando extends Thread {
    
    private String mensaje;
    private Cliente cliente;
    
    public TaskLeerComando(String mensaje, Cliente cliente) {
        this.mensaje = mensaje;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        //IDENTIFICAR COMANDO
        switch (ClienteUtils.LeerCOMANDO(mensaje)){
            case ClienteUtils.COMSERV_IDENTIFICACION:
                //asignamos el ID al cliente
                ClienteUtils.AsignarIDConexion(mensaje, cliente);
                
                //notificamos la llegada del ID
                Despachador.DespacharEventoIdentificado(null);
                break;
            case ClienteUtils.COMSERV_LOGIN_OK:
                //Leemos la respuesta 0,1 para verificar si pudimos loguearnos
                        
                //notificamos la respuesta
                Despachador.DespacharEventoLoginOK(new EventoMensaje(this, new Mensaje(mensaje, cliente)));
                break;
            case ClienteUtils.COMSERV_REGISTRO_OK:
                //Leemos la respuesta 0,1 para verificar si pudimos registrarnos
                
                //notificamos la respuesta
                Despachador.DespacharEventoRegistroOK(new EventoMensaje(this, new Mensaje(ClienteUtils.LeerOK(mensaje), cliente)));
                break;
                
                
            case ClienteUtils.COMSERV_COMERZARPARTIDA_OK:
                Despachador.DespacharEventoComerzarPartidaOK(new EventoMensaje(this, new Mensaje(mensaje, cliente)));
                break;
                
            case ClienteUtils.COMSERV_UNJUGADOR_SE_DESCONECTO:
                Despachador.DespacharEventoUnJugadorSeDesconecto(new EventoMensaje(this, new Mensaje(mensaje, cliente)));
                break;
                
            case ClienteUtils.COMSERV_UNJUGADOR_SE_CONECTO:
                Despachador.DespacharEventoUnJugadorSeConecto(new EventoMensaje(this, new Mensaje(mensaje, cliente)));
                break;
                
            case ClienteUtils.COMSERV_GASTARTURNO_OK:
                Despachador.DespacharEventoGastarTurnoOK(new EventoMensaje(this, new Mensaje(mensaje, cliente)));
                break;
                
            case ClienteUtils.COMSERV_PARTIDA_ENCURSO:
                Despachador.DespacharEventoHayPartidaEnCurso(new EventoMensaje(this, new Mensaje(mensaje, cliente)));
                break;
            default:
                System.out.println("Comando no encontrado!!!");
        }
    }
    
}
