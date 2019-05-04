/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.usuario.controlador;

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
public class UsuarioControlador implements Serializable{
    Usuarios usuario = new Usuarios();
    UsuarioServicio usuarioServicio = new UsuarioServicio();
    private String redireccion;
    
    
    public void  acceso(){
        try{
            usuario = usuarioServicio.consulta(1, usuario);

            if(usuario.getEstatus().equalsIgnoreCase("A")){
                redireccion = "principal.xhtml";
            }else{
                System.out.println("Entro aca");
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
            Usuarios usuarioVive = (Usuarios) contexto.getExternalContext().getSessionMap().get("usuario");
            
            if (usuarioVive.getClave()== null) {
                contexto.getExternalContext().redirect("index.xhtml");  
            }else{
               usuario = usuarioVive; 
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
    
    
    
}
