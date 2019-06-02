/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.usuario.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import sice.geneal.modelo.MensajeSalida;
import sice.usuario.modelo.Usuarios;
import sice.usuario.servicio.UsuarioServicio;

/**
 *
 * @author vplei
 */
@ManagedBean
@ViewScoped
public class UsuarioControlador implements Serializable{
    Usuarios usuario = new Usuarios();
    UsuarioServicio usuarioServicio = new UsuarioServicio();

   
    
   
    
    
    public void transaccion(int accion){
        MensajeSalida mensaje = new MensajeSalida();
        FacesMessage message;
        Usuarios audUsuario = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        usuario.setAudUsuarioID(Integer.parseInt(audUsuario.getUsuarioID()));
        usuario.setClaveUsuario(audUsuario.getClave());
        usuario.setNumeroIP("127.0.0.1");
        usuario.setPrograma("Alta.usuario");
        try{
        mensaje = usuarioServicio.transaccion(usuario, accion);
        if(mensaje.getNumErr()==0){
          message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", mensaje.getErrMen()+" "+mensaje.getConsecutivo());
           //PrimeFaces.current().resetInputs("form:panel");
          usuario.setUsuarioID(String.valueOf(mensaje.getConsecutivo()));
         
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: "+mensaje.getNumErr(), mensaje.getErrMen());
        }
        PrimeFaces.current().dialog().showMessageDynamic(message);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public List<Usuarios> lista(String nombre){
        List<Usuarios> lista = new ArrayList<Usuarios>();
        usuario.setUsuarioID(nombre);
        try{
            lista = usuarioServicio.listaUsuario(usuario,1);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }
    
    
    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }


    
    
    
}
