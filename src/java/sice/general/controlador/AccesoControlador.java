/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.general.controlador;

import java.io.Serializable;
import java.net.InetAddress;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import sice.general.modelo.Acceso;
import sice.general.modelo.MensajeSalida;
import sice.general.servicio.AccesoServicio;
import sice.usuario.modelo.Usuarios;



/**
 *
 * @author vplei
 */

@ManagedBean
@ViewScoped
public class AccesoControlador implements Serializable{
    Acceso acceso = new Acceso();
    Usuarios usuario = new Usuarios();
    AccesoServicio accesoServicio = new AccesoServicio();
    private String redireccion;
    private String nombreCompleto;
    String clave;
    String pass;
    
    public interface Acceso_Actualiza{
        int actualizaPass   = 1;
    }
    
    public void  accesoSistema(){
        
         try{
            acceso = accesoServicio.consulta(clave,pass);

            if(acceso.getUsuario().getEstatus().equalsIgnoreCase("A")){
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", acceso.getUsuario());
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
            Usuarios usuarioVive; 
             
             usuarioVive = (Usuarios) contexto.getExternalContext().getSessionMap().get("usuario");
             nombreCompleto = usuarioVive.getNombreCompleto();
            if (usuarioVive ==null) {
               contexto.getExternalContext().redirect(contexto.getExternalContext().getRequestContextPath());
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void validaPrimerAcceso(){
        try{
             FacesContext contexto = FacesContext.getCurrentInstance();
            Usuarios usuarioVive;  
             usuarioVive = (Usuarios) contexto.getExternalContext().getSessionMap().get("usuario");

             if(String.valueOf(usuarioVive.getPrimerAcceso()).equals("1900-01-01")){

                PrimeFaces.current().executeScript("PF('cambioPass').show();");
                
             }
             
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    public void cambiaContrasenia(){
        FacesContext contexto = FacesContext.getCurrentInstance();
       MensajeSalida mensaje = new MensajeSalida();
        FacesMessage message = null;
         InetAddress address;
        try{
        /*Estos campos se incluyen unicamente con altas, modificaciones, actualizaciones o Eliminaciones+*/
        Usuarios audUsuario = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        address = InetAddress.getLocalHost();
        usuario.setEstatus(audUsuario.getEstatus());
        usuario.setUsuarioID(audUsuario.getUsuarioID());
        usuario.setAudUsuarioID(Integer.parseInt(audUsuario.getUsuarioID()));
        usuario.setClaveUsuario(audUsuario.getClave());
        usuario.setNumeroIP( address.getHostAddress());
        usuario.setPrograma("Principal.CambioPassword");
     
        mensaje = accesoServicio.actualiza(usuario, Acceso_Actualiza.actualizaPass);
        if(mensaje.getNumErr()==0){
          contexto.getExternalContext().redirect(contexto.getExternalContext().getRequestContextPath());
         
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: "+mensaje.getNumErr(), mensaje.getErrMen());
            PrimeFaces.current().dialog().showMessageDynamic(message);
        }
        
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public Acceso getAcceso() {
        return acceso;
    }

    public void setAcceso(Acceso acceso) {
        this.acceso = acceso;
    }
    
    public String getRedireccion() {
        return redireccion;
    }

    public void setRedireccion(String redireccion) {
        this.redireccion = redireccion;
    }


    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    
}
