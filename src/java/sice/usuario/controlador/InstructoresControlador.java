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
import sice.general.modelo.MensajeSalida;
import sice.herramientas.Herramientas;
import sice.usuario.modelo.Instructores;
import sice.usuario.modelo.Usuarios;
import sice.usuario.servicio.InstructoresServicio;
import sice.usuario.servicio.UsuarioServicio;

/**
 *
 * @author vplei
 */
@ManagedBean
@ViewScoped
public class InstructoresControlador implements Serializable{
    
    Instructores instructor = new Instructores();
    Usuarios usuario = new Usuarios();
    Herramientas herramientas = new Herramientas();
    InstructoresServicio instructoresServicio = new InstructoresServicio();
    UsuarioServicio usuarioServicio = new UsuarioServicio();
    private boolean agregar = true;
    private boolean modificar = true;
    
    public void transaccion(int accion){
        MensajeSalida mensaje = new MensajeSalida();
         FacesMessage message;
         InetAddress address;
         try{
                Usuarios audUsuario = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
                address = InetAddress.getLocalHost();
                instructor.setAudUsuarioID(Integer.parseInt(audUsuario.getUsuarioID()));
                instructor.setClaveUsuario(audUsuario.getClave());
                instructor.setNumeroIP( address.getHostAddress());
                instructor.setPrograma("Registro.Instructor");
                instructor.setUsuarioID(usuario);
                mensaje = instructoresServicio.transaccion(accion, instructor);
                if(mensaje.getNumErr()==0){
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", mensaje.getErrMen()+" "+mensaje.getConsecutivo());
                    usuario.setUsuarioID(String.valueOf(mensaje.getConsecutivo()));

                }else{
                      message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: "+mensaje.getNumErr(), mensaje.getErrMen());
                    }
                  PrimeFaces.current().dialog().showMessageDynamic(message);
         }catch(Exception ex){
             ex.printStackTrace();
         }
    }
    
    public List<Instructores> lista(String cadena){
        List<Instructores> lista = new ArrayList<Instructores>();
        instructor.setInstructorID(cadena);
        try{
            lista = instructoresServicio.lista(1,instructor);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }
    
    
    public void consultaInstructores(){       
        try{
           if(herramientas.esNumero(instructor.getInstructorID())==true){ 
               System.out.println("INSTRUCTORID "+instructor.getInstructorID());
                if(Integer.parseInt(instructor.getInstructorID())!=0){
                    instructor = instructoresServicio.consulta(1,instructor);
                    System.out.println("Instructor consultado "+instructor.getUsuarioID().getUsuarioID());
                    usuario =instructor.getUsuarioID();
                    consultaUsuario();
                    modificar = false;
                    agregar = true;
                }else{
                    instructor.resetea();
                    agregar = false;
                    modificar = true;
                }
           }else{
                    instructor.resetea();
                    agregar = true;
                    modificar = true; 
           }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    } 

     public void consultaUsuario(){       
        try{
           if(herramientas.esNumero(usuario.getUsuarioID())==true){ 
               
                if(Integer.parseInt(usuario.getUsuarioID())!=0){
                    usuario = usuarioServicio.consulta(2, usuario);
                }else{
                    usuario.resetea();
                }
           }else{
                    usuario.resetea();
           }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    public Instructores getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructores instructor) {
        this.instructor = instructor;
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

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    
}
