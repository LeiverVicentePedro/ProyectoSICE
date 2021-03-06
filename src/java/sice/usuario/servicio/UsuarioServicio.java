/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.usuario.servicio;

import java.util.ArrayList;
import java.util.List;
import sice.general.modelo.MensajeSalida;
import sice.usuario.dao.UsuarioDAO;
import sice.usuario.modelo.Usuarios;

/**
 *
 * @author vplei
 */
public class UsuarioServicio {
    
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    public static interface usuario_num_Tran{
        int registro = 1;
        int modificacion = 2;
    }
    
    public static interface usuario_num_Lis{
        int lisprincipal =1;
    }
    
    public static interface usuario_num_Con{
        int conPrincipal =2;
    }
    public MensajeSalida transaccion(Usuarios usuario,int numTransaccion){
        MensajeSalida mensaje = new MensajeSalida(); 
        try{
            switch(numTransaccion){
                case usuario_num_Tran.registro:
                    mensaje = usuarioDAO.altaUsuario(usuario);
                break;
                 case usuario_num_Tran.modificacion:
                    mensaje = usuarioDAO.modificaUsuario(usuario);
                break;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return mensaje;
    }
    
    public Usuarios consulta(int consulta,Usuarios usuario){
        Usuarios usuarioCon = new Usuarios();
        try{
            switch(consulta){
                case usuario_num_Con.conPrincipal:
                    usuarioCon = usuarioDAO.ConsultaUsuario(usuario, consulta);
                break;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            
        }
        return usuarioCon;
    }
    
    public List<Usuarios> listaUsuario(int numLista,Usuarios usuario){
        List<Usuarios> lista = new ArrayList<Usuarios>();
        try{
            switch(numLista){
                case usuario_num_Lis.lisprincipal:
                    lista = usuarioDAO.listaPrincipal(usuario, usuario_num_Lis.lisprincipal);
                break;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }
    
}
