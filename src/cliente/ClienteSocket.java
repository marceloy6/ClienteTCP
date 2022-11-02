/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import cliente.escuchadores.EscuchadorConexion;
import cliente.escuchadores.EscuchadorMensajes;
import cliente.eventos.Cliente;
import cliente.eventos.EventoCliente;
import cliente.eventos.EventoMensaje;
import cliente.hilos.HiloConexion;
import cliente.hilos.HiloMensajesServidor;
import cliente.hilos.TaskEnviarMensaje;
import cliente.hilos.TaskLeerComando;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Marcelo
 */
public class ClienteSocket implements EscuchadorConexion, EscuchadorMensajes{
    
    private EventoCliente eventoClienteS;
    private HiloConexion hiloConexion;
    private ExecutorService ejecutor;
    
    public ClienteSocket(String host, int puerto) {
        eventoClienteS = new EventoCliente(this, new Cliente(host, puerto));
        hiloConexion = new HiloConexion(eventoClienteS);
        ejecutor = Executors.newCachedThreadPool();
        
        Despachador.listaEscuchadores.add(EscuchadorConexion.class, this);
        Despachador.listaEscuchadores.add(EscuchadorMensajes.class, this);
    }
    
    public void Iniciar() {
        hiloConexion.start();
    }
    
    public void Detener() {
        System.out.println("Deteniendo Cliente...");
        eventoClienteS.Detener();
        hiloConexion.Detener();
        
        System.out.println("Cliente Detenido !!");
    }

    public EventoCliente getEventoClienteS() {
        return eventoClienteS;
    }

    public void setEventoClienteS(EventoCliente eventoClienteS) {
        this.eventoClienteS = eventoClienteS;
    }
    
    public void Loguear(String user, String password) {
        String mensaje = ClienteUtils.COMCLI_LOGIN + "," +
                "ID=" + eventoClienteS.getCliente().getId() + "," +
                "user=" + user + "," +
                "pass=" + password;
        
        Future future = ejecutor.submit(new TaskEnviarMensaje(eventoClienteS.getCliente().getFlujosalida(), mensaje));
    }
    
    public void Registrar(String user, String password) {
        String mensaje = ClienteUtils.COMCLI_REGISTRAR + "," +
                "ID=" + eventoClienteS.getCliente().getId() + "," +
                "user=" + user + "," +
                "pass=" + password;
        
        Future future = ejecutor.submit(new TaskEnviarMensaje(eventoClienteS.getCliente().getFlujosalida(), mensaje));
    }
    
    public void ComenzarPartida() {
        String mensaje = ClienteUtils.COMCLI_COMENZARPARTIDA + "," +
                "ID=" + eventoClienteS.getCliente().getId();
        Future future = ejecutor.submit(new TaskEnviarMensaje(eventoClienteS.getCliente().getFlujosalida(), mensaje));
    }
    
    public void GastarTurno() {
        String mensaje = ClienteUtils.COMCLI_GASTARTURNO + "," +
                "ID=" + eventoClienteS.getCliente().getId();
        Future future = ejecutor.submit(new TaskEnviarMensaje(eventoClienteS.getCliente().getFlujosalida(), mensaje));
    }
    
    public void GastarTurnoConJugada(String jugada) {
        String mensaje = ClienteUtils.COMCLI_GASTARTURNO + "," +
                "ID=" + eventoClienteS.getCliente().getId() + 
                jugada;
        Future future = ejecutor.submit(new TaskEnviarMensaje(eventoClienteS.getCliente().getFlujosalida(), mensaje));
    }

    @Override
    public void OnConectado(EventoCliente eventoCliente) {
        eventoCliente.getCliente().setConectado(true);
        eventoCliente.getCliente().setHiloMensajesServidor(new HiloMensajesServidor(eventoCliente));
        eventoCliente.getCliente().getHiloMensajesServidor().start();
        
        
    }

    @Override
    public void OnDesconectado(EventoCliente eventoCliente) {
        System.out.println("Desconectado !!!");
        eventoCliente.getCliente().Desconectar();
        
        hiloConexion = new HiloConexion(eventoCliente);
        hiloConexion.start();
    }

    @Override
    public void OnMensajeRecibido(EventoMensaje eventoMensaje) {
        System.out.println("Servidor Dice> " + eventoMensaje.getMensaje().getMensajeServidor());
        
        //INTERPRETAMOS EL MENSAJE
        Future future = ejecutor.submit(new TaskLeerComando(eventoMensaje.getMensaje().getMensajeServidor(), eventoClienteS.getCliente()));
    }

    @Override
    public void OnIdentificado(EventoMensaje eventoMensaje) {
        //
    }

    @Override
    public void OnLoginOK(EventoMensaje eventoMensaje) {
        //
        eventoClienteS.getCliente().setUser(eventoMensaje.getMensaje().getMensajeServidor());
    }

    @Override
    public void OnRegistroOK(EventoMensaje eventoMensaje) {
        //
    }

    @Override
    public void OnComerzarPartidaOK(EventoMensaje eventoMensaje) {
        //
    }

    @Override
    public void OnUnJugadorDesconectado(EventoMensaje eventoMensaje) {
        //
    }

    @Override
    public void OnUnJugadorConectado(EventoMensaje eventoMensaje) {
        //
    }

    @Override
    public void OnGastarTurnoOK(EventoMensaje eventoMensaje) {
        //
    }

    @Override
    public void OnHayPartidaEnCurso(EventoMensaje eventoMensaje) {
        //
    }
    
}
