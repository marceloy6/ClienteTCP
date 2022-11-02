/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import cliente.eventos.Cliente;
import java.util.ArrayList;
import java.util.List;
import juego.negocio.JuegoCompartido;
import juego.negocio.Jugada;
import juego.negocio.Jugador;

/**
 *
 * @author Marcelo
 */
public class ClienteUtils {
    
    //CAMPOS DE USUARIO
    public static final String USER_ID = "ID=";
    public static final String USER_USER = "user=";
    public static final String USER_PASS = "pass=";
    
    public static final char CHAR_DELIMITADOR = ',';
    public static final char CHAR_IGUALDAD = '=';
    
    public static final String REGEX_DELIMITADOR = "\\s*,\\s*";
    public static final String REGEX_IGUAL = "\\s*=\\s*";
    public static final String REGEX_Y = "\\s*&\\s*";
    
    //COMANDOS 
    //envio
    public static final String COMCLI_LOGIN = "#LOGIN";
    public static final String COMCLI_REGISTRAR = "#REGISTRAR";
    public static final String COMCLI_COMENZARPARTIDA = "#COMENZARPARTIDA";
    public static final String COMCLI_GASTARTURNO = "#GASTARTURNO";
    
    //recepcion
    public static final String COMSERV_IDENTIFICACION = "#CONEXION";
    public static final String COMSERV_LOGIN_OK = "#LOGINOK";
    public static final String COMSERV_REGISTRO_OK = "#REGISTROOK";
    public static final String COMSERV_COMERZARPARTIDA_OK = "#COMENZARPARTIDAOK";
    public static final String COMSERV_UNJUGADOR_SE_CONECTO = "#UNJUGADORCONECTADO";
    public static final String COMSERV_UNJUGADOR_SE_DESCONECTO = "#UNJUGADORDESCONECTADO";
    public static final String COMSERV_GASTARTURNO_OK = "#GASTARTURNOOK";
    public static final String COMSERV_PARTIDA_ENCURSO = "#HAY_PARTIDA_EN_CURSO";
    
    
    public static String LeerCOMANDO(String mensaje) {
        return mensaje.trim().split(REGEX_DELIMITADOR)[0];
    }
    
    public static void AsignarIDConexion(String mensaje, Cliente cliente) {
        //#CONEXION,ID=277234535
        String valor = LeerValorCampo(mensaje.split(REGEX_DELIMITADOR)[1]);
        cliente.setId(Long.parseLong(valor));
    }
    
    public static String LeerOK(String mensaje) {
        //leemos solo el 2do valor, el es la confirmacion, el cual es un entero
        //#COMSERV,1,ID=65468465
        //#COMSERV,0,ID=-1;
        return mensaje.split(REGEX_DELIMITADOR)[1];
    }
    
    public static String LeerOKnewID(String mensaje) {
        //#COMSERV,1,ID=65468465
        //#COMSERV,0,ID=-1;
        return LeerValorCampo( mensaje.split(REGEX_DELIMITADOR)[2] );
    }
    
    
    public static void ActualizarMiJugador(List<Jugador> listaJugadores, Jugador jugador) {
        for (Jugador j : listaJugadores) {
            if (j.getId() == jugador.getId()) {
                jugador.setTurno(j.isTurno());
                jugador.setConectado(j.isConectado());
            }
        }
    }
    
    public static void ActualizarListaFromMensajePostTurno(String mensaje, Jugador jugadorYO) {
        //#GASTARTURNO_OK ,ID=856161612&SENAS=24 ,iduser=856161612&user=ana&conectado=true&turno=false&ganador=false 
        //                                       ,iduser=856161620&user=juan&conectado=true&turno=true&ganador=false
        //                                       ,iduser=856161640&user=pedro&conectado=true&turno=false&ganador=false
        String[] tokensVal = mensaje.split(REGEX_DELIMITADOR);
        
        //leyendo al iduser y su jugada
        long idEmisor = Long.parseLong( LeerValorCampo( LeerCampoIDuserPostTurno(tokensVal[1]) ));
        String campoJugadaEmisor = LeerCampoJugadaPostTurno(tokensVal[1]);
        
        for (int i = 2; i < tokensVal.length; i++) {
            
            Jugador jugadorLeido = LeerJugadorPostTurno(tokensVal[i]);
            
            if (jugadorLeido.getId() == jugadorYO.getId()) {
                JuegoCompartido.ActualizarJugadorEnLista(jugadorLeido);
            } else {
                if (jugadorLeido.getId() == idEmisor) {
                    JuegoCompartido.ActualizarJugadorEnLista(jugadorLeido, LeerNombreCampo(campoJugadaEmisor), Integer.parseInt(LeerValorCampo(campoJugadaEmisor)));
                } else {
                    JuegoCompartido.ActualizarJugadorEnLista(jugadorLeido);
                }
            }
        }
    }
    
    private static Jugador LeerJugadorPostTurno(String s) {
        Jugador j = new Jugador();
        String[] tokensVal = s.split(REGEX_Y);
        
        // iduser=1561616561 & user=juan & conectado=true & turno=true & ganador=false
        j.setId(Long.parseLong(LeerValorCampo(tokensVal[0])));
        j.setNombre(LeerValorCampo(tokensVal[1]));
        j.setConectado(Boolean.parseBoolean(LeerValorCampo(tokensVal[2])));
        j.setTurno(Boolean.parseBoolean(LeerValorCampo(tokensVal[3])));
        j.setGanador(Boolean.parseBoolean(LeerValorCampo(tokensVal[4])));
        
        return j;
    }
    
    private static String LeerCampoJugadaPostTurno(String s) {
        // ID=51651616516&SENAS=24
        return s.split(REGEX_Y)[1];
    }
    
    private static String LeerCampoIDuserPostTurno(String s) {
        return s.split(REGEX_Y)[0];
    }
    
    
    public static ArrayList<Jugador> LeerListaJugadoresExcluyendoTablero(String mensaje) {
        //#COMENZARPARTIDAOK    ,iduser=1561616561&user=juan&conectado=true, iduser=1684165161&user=pedro&conectado=false
        //#COMENZARPARTIDAOK    ,iduser=4981651984&user=juan&conectado=true&turno=true, iduser=1561616561&user=juan&conectado=true&turno=true, iduser=1684165161&user=pedro&conectado=false&turno=false
        //#UN_JUGADOR_CONECTADO ,iduser=4981651984&user=juan&conectado=true&turno=true
        ArrayList<Jugador> lista = new ArrayList<>();
        
        String[] tokensVal = mensaje.split(REGEX_DELIMITADOR);
        for (int i = 1; i < tokensVal.length; i++) {
            lista.add(LeerJugadorExcluyendoTablero(tokensVal[i]));
        }
        return lista;
    }
    
    public static Jugador LeerJugadorExcluyendoTablero(String s) {
        //iduser=1561616561 & user=juan & conectado=true & turno=false
        //iduser=1561616561 & user=juan & conectado=true & turno=false & ganador=false
        Jugador j = new Jugador();
        String[] tokensVal = s.split(REGEX_Y);
        
        //iduser=1561616561
        j.setId(Long.parseLong(LeerValorCampo(tokensVal[0])));
        j.setNombre(LeerValorCampo(tokensVal[1]));
        j.setConectado(Boolean.parseBoolean(LeerValorCampo(tokensVal[2])));
        j.setTurno(Boolean.parseBoolean(LeerValorCampo(tokensVal[3])));
        
        return j;
    }
    
    public static String LeerValorCampo(String XigualValor) {
        //X=a;
        return XigualValor.split(REGEX_IGUAL)[1];
    }
    
    public static String LeerNombreCampo(String XigualValor) {
        //X=a;
        return XigualValor.split(REGEX_IGUAL)[0];
    }
    
}
