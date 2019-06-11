/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.herramientas;

/**
 *
 * @author vplei
 */
public class Herramientas {
    
    
    public static boolean esNumero(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    public String PonerMayuscula(String cadena){
        String cadenaSalida = null;
            cadenaSalida = cadena.toUpperCase();
        return cadenaSalida;
    }
}
