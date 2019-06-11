/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.usuario.controlador;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import sice.geneal.modelo.MensajeSalida;
import sice.herramientas.Herramientas;
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
    Herramientas herramientas = new Herramientas();
    
    private boolean agregar = true;
    private boolean modificar = true;
    
    
    public void transaccion(int accion){
        MensajeSalida mensaje = new MensajeSalida();
        FacesMessage message;
         InetAddress address;
        try{
        /*Estos campos se incluyen unicamente con altas, modificaciones, actualizaciones o Eliminaciones+*/
        Usuarios audUsuario = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        address = InetAddress.getLocalHost();
        usuario.setAudUsuarioID(Integer.parseInt(audUsuario.getUsuarioID()));
        usuario.setClaveUsuario(audUsuario.getClave());
        usuario.setNumeroIP( address.getHostAddress());
        usuario.setPrograma("Registro.Usuario");
        
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
    
    public List<Usuarios> lista(String cadena){
        List<Usuarios> lista = new ArrayList<Usuarios>();
        usuario.setUsuarioID(cadena);
        try{
            lista = usuarioServicio.listaUsuario(1,usuario);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }
    
    public void consulta(){       
        try{
           if(herramientas.esNumero(usuario.getUsuarioID())==true){ 
               
                if(Integer.parseInt(usuario.getUsuarioID())!=0){
                    usuario = usuarioServicio.consulta(2, usuario);
                    modificar = false;
                    agregar = true;
                }else{
                    usuario.resetea();
                    agregar = false;
                    modificar = true;
                }
           }else{
                    usuario.resetea();
                    agregar = true;
                    modificar = true;
           }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public boolean isAgregar() {
        return agregar;
    }

    public void setAgregar(boolean agregar) {
        this.agregar = agregar;
    }

    public boolean isModificar() {
        return modificar;
    }

    public void setModificar(boolean modificar) {
        this.modificar = modificar;
    }
   
}
