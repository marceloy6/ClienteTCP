/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.eventos;

import cliente.hilos.HiloMensajesServidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo
 */
public class Cliente {
    
    private long id;
    private String user;
    private String password;
    
    private boolean login;
    private boolean conectado;
    
    private Socket socketCliente;
    private DataInputStream flujoentrada;
    private DataOutputStream flujosalida;
    
    private HiloMensajesServidor hiloMensajesServidor;
    
    private String host;
    private int puerto;
    
    public Cliente(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
        id = -1;
        user = "-1";
        password = "-1";
        login = false;
        conectado = false;
        socketCliente = null;
        flujoentrada = null;
        flujosalida = null;
        hiloMensajesServidor = null;
    }

    public Cliente(Socket socket) {
        id = -1;
        user = "-1";
        password = "-1";
        login = false;
        this.socketCliente = socket;
        
        try {
            flujoentrada = new DataInputStream(socket.getInputStream());
            flujosalida = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Socket getSocketCliente() {
        return socketCliente;
    }

    public void setSocketCliente(Socket socketCliente) {
        try {
            this.socketCliente = socketCliente;
            flujoentrada = new DataInputStream(socketCliente.getInputStream());
            flujosalida = new DataOutputStream(socketCliente.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public DataInputStream getFlujoentrada() {
        return flujoentrada;
    }

    public void setFlujoentrada(DataInputStream flujoentrada) {
        this.flujoentrada = flujoentrada;
    }

    public DataOutputStream getFlujosalida() {
        return flujosalida;
    }

    public void setFlujosalida(DataOutputStream flujosalida) {
        this.flujosalida = flujosalida;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public HiloMensajesServidor getHiloMensajesServidor() {
        return hiloMensajesServidor;
    }

    public void setHiloMensajesServidor(HiloMensajesServidor hiloMensajesServidor) {
        this.hiloMensajesServidor = hiloMensajesServidor;
    }
    
    
    

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
    
    public void Limpiar() {
        user = "-1";
        password = "-1";
    }
    
    public void Desconectar() {
        try {
            conectado = false;
            if (hiloMensajesServidor != null)
                hiloMensajesServidor.Detener();
            if (socketCliente != null && flujoentrada != null && flujosalida != null ) {
                socketCliente.close();
                flujoentrada.close();
                flujosalida.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
