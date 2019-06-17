/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.usuario.modelo;

import java.io.Serializable;
import sice.herramientas.AuditoriasBean;

/**
 *
 * @author vplei
 */
public class Instructores extends AuditoriasBean implements Serializable{
    private String instructorID;
    private String clave;
    private Usuarios usuarioID;
    private String estatus;
    
    private String NombreCompleto;

    public String getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(String instructorID) {
        this.instructorID = instructorID;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Usuarios getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Usuarios usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }
    
    
    
    public void resetea(){
       instructorID =   null;
       clave        =   null;
       usuarioID    =   null;
       estatus      =   null; 
    }
    
}
