/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.general.servicio;

import sice.geneal.dao.AccesoDAO;
import sice.geneal.modelo.Acceso;
import sice.geneal.modelo.MensajeSalida;
import sice.usuario.modelo.Usuarios;


/**
 *
 * @author vplei
 */
public class AccesoServicio {
    AccesoDAO accesoDAO = new AccesoDAO();
    public interface Acceso_Actualiza{
        int actualizaPass   = 1;
    }
    public Acceso consulta(String clave,String pass){
        Acceso usuarioCon = new Acceso();
        try{   
               usuarioCon = accesoDAO.ConsultaAcceso(clave, pass);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return usuarioCon;
    }
    
    public MensajeSalida actualiza(Usuarios usuario,int numTransaccion){
        MensajeSalida mensaje = new MensajeSalida(); 
        try{

            mensaje = accesoDAO.actualizaPassword(usuario,Acceso_Actualiza.actualizaPass);

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return mensaje;
    } 
}
