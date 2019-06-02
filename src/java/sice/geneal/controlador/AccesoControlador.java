/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.geneal.controlador;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sice.usuario.modelo.Usuarios;
import sice.usuario.servicio.UsuarioServicio;

/**
 *
 * @author vplei
 */
@ManagedBean
@ViewScoped
public class AccesoControlador implements Serializable{
    Usuarios usuario = new Usuarios();
    UsuarioServicio usuarioServicio = new UsuarioServicio();
    private String redireccion;
    Usuarios usuarioVive;
    
     public void  acceso(){
        try{
            usuario = usuarioServicio.consulta(1, usuario);

            if(usuario.getEstatus().equalsIgnoreCase("A")){
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
                redireccion = "principal";
            }else{
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Acceso Denegado","Clave no Valida."));
            }

        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Acceso Denegado","Clave o Contraseña Incorrecta"));
            ex.printStackTrace();
        }
    }
      public void exite() {
        try {
            FacesContext contexto = FacesContext.getCurrentInstance();
              
             usuarioVive = (Usuarios) contexto.getExternalContext().getSessionMap().get("usuario");
            if (usuarioVive ==null) {
               contexto.getExternalContext().redirect("index");
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
      }
     
     
     
     
    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
     
    public String getRedireccion() {
        return redireccion;
    }

    public void setRedireccion(String redireccion) {
        this.redireccion = redireccion;
    }

    public Usuarios getUsuarioVive() {
        return usuarioVive;
    }

    public void setUsuarioVive(Usuarios usuarioVive) {
        this.usuarioVive = usuarioVive;
    }
    
}
