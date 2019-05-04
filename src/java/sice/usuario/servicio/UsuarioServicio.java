/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.usuario.servicio;

import sice.usuario.dao.UsuarioDAO;
import sice.usuario.modelo.Usuarios;

/**
 *
 * @author vplei
 */
public class UsuarioServicio {
    
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public Usuarios consulta(int consulta,Usuarios usuario){
        Usuarios usuarioCon = new Usuarios();
        try{
            switch(consulta){
                case 1:
                    usuarioCon = usuarioDAO.ConsultaAcceso(usuario, consulta);
                break;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            
        }
        return usuarioCon;
    }
    
}
