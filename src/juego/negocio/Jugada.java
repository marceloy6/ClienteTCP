/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.negocio;

/**
 *
 * @author Marcelo
 */
public class Jugada {
    
    public static final String BALAS = "BALAS"; //radbtn_balas
    public static final String TONTOS = "TONTOS";
    public static final String TRENES = "TRENES";
    public static final String CUADRAS = "CUADRAS";
    public static final String QUINAS = "QUINAS";
    public static final String SENAS = "SENAS";
    
    public static final String ESCALERA = "ESCALERA";
    public static final String FULL = "FULL";
    public static final String POKER = "POKER";
    
    public static final String GRANDE = "GRANDE";
    
    public static final String GRANDE1 = "GRANDE1";
    public static final String GRANDE2 = "GRANDE2";
    
    
    
    public static final int ERROR_CASILLA_LLENA = 100;
    public static final int ERROR_ESCALERA = 12345;
    public static final int ERROR_FULL = 22333;
    public static final int ERROR_POKER = 11112;
    public static final int ERROR_GRANDE = 11111;
    public static final int ERROR_GRANDE_LLENA = 22222;
    
    public static int InsertarJugada(Jugador jugador, Cubilete cubilete, String nombreJugada) {
        if(!jugador.HayEspacioEnCasilla(nombreJugada))
            return ERROR_CASILLA_LLENA;
        int res = 0;
        switch(nombreJugada) {
            case BALAS:
                jugador.PonerBala(cubilete.ObtenerSumaDeLosDadosConValor(1));
                break;
            case TONTOS:
                jugador.PonerTonto(cubilete.ObtenerSumaDeLosDadosConValor(2));
                break;
            case TRENES:
                jugador.PonerTren(cubilete.ObtenerSumaDeLosDadosConValor(3));
                break;
            case CUADRAS:
                jugador.PonerCuadra(cubilete.ObtenerSumaDeLosDadosConValor(4));
                break;
            case QUINAS:
                jugador.PonerQuina(cubilete.ObtenerSumaDeLosDadosConValor(5));
                break;
            case SENAS:
                jugador.PonerSena(cubilete.ObtenerSumaDeLosDadosConValor(6));
                break;
            
            case ESCALERA:
                if (esEscalera(cubilete)) {
                    if (cubilete.getLanzamientos() == 1) {
                        jugador.PonerEscalera(25);
                    } else {
                        jugador.PonerEscalera(20);
                    }
                } else {
                    res = ERROR_ESCALERA;
                }
                break;
            case FULL:
                if (esFull(cubilete)) {
                    if (cubilete.getLanzamientos() == 1) {
                        jugador.PonerFull(35);
                    } else {
                        jugador.PonerFull(30);
                    }
                } else {
                    res = ERROR_FULL;
                }
                break;
            case POKER:
                if (esPoker(cubilete)) {
                    if (cubilete.getLanzamientos() == 1) {
                        jugador.PonerPoker(45);
                    } else {
                        jugador.PonerPoker(40);
                    }
                } else {
                    res = ERROR_POKER;
                }
                break;
            case GRANDE:
                if (esGrande(cubilete)) {
                    if (cubilete.getLanzamientos() == 1) {
                        jugador.setGanador(true);
                        if (!jugador.PonerGrande(50) )
                            res = ERROR_GRANDE_LLENA;
                    } else {
                        if (!jugador.PonerGrande(50) )
                            res = ERROR_GRANDE_LLENA;
                    }
                } else {
                    res = ERROR_GRANDE;
                }
                break;
        }
        
        return res;
    }
    
    private static boolean esEscalera(Cubilete cubilete) {
        if (losDadosSonDiferentes(cubilete)) {
            if (tieneEl34y5(cubilete)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean esFull(Cubilete cubilete) {
        for (Dado dado : cubilete.getDados()) {
            
            int acu = 0;
            for (Dado dado1 : cubilete.getDados()) {
                if (!dado1.isMarcado()) {
                    if (dado1.getValor() == dado.getValor()) {
                        dado1.setMarcado(true);
                        acu++;
                    }
                }
            }
            
            if (acu == 0)
                acu = 2;
            if (acu < 2 || acu > 3) {
                cubilete.ResetMarcadoDeDados();
                return false;
            }
        }
        cubilete.ResetMarcadoDeDados();
        return true;
    }
    
    private static boolean esPoker(Cubilete cubilete) {
        for (Dado dado : cubilete.getDados()) {
            int acu = 0;
            for (Dado dado1 : cubilete.getDados()) {
                if (dado1.getValor() == dado.getValor()) {
                    acu++;
                    if (acu == 4) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean esGrande(Cubilete cubilete) {
        boolean res = true;
        int unvalor = cubilete.getDado(0).getValor();
        for (Dado dado : cubilete.getDados()) {
            if (dado.getValor() != unvalor) {
                res = false;
                break;
            }
        }
        return res;
    }
    
    
    
    
    
    private static boolean losDadosSonDiferentes(Cubilete cubilete) {
        Dado dado = cubilete.getDado(0);
        for (int i = 1; i < cubilete.getNroDados(); i++) {
            if (dado.getValor() == cubilete.getDado(i).getValor()) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean tieneEl34y5(Cubilete cubilete) {
        return tieneElNro(cubilete, 3) && tieneElNro(cubilete, 4) && tieneElNro(cubilete, 5);
    }
    
    private static boolean tieneElNro(Cubilete cubilete, int nro) {
        for (Dado dado : cubilete.getDados()) {
            if (dado.getValor() == nro)
                return true;
        }
        return false;
    }
    
}
