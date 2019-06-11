/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.usuario.modelo;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author vplei
 */
public class Usuarios implements Serializable{

    
    private String usuarioID;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String sexo;
    private Date fechaNacimiento;
    private Date primerAcceso;
    private String numControl;
    private String nombreCompleto;
    private int establecimientoID;
    private String correo;
    private String clave;
    private String contrasenia;
    private String confirmacion;
    private int rolID;
    private String estatus;

    
    /*SECCION DE AUDITORIA*/
   private int audUsuarioID;
   private String claveUsuario;
   private String NumeroIP;
   private String Programa;

    
    
    
    
    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getPrimerAcceso() {
        return primerAcceso;
    }

    public void setPrimerAcceso(Date primerAcceso) {
        this.primerAcceso = primerAcceso;
    }

    public String getNumControl() {
        return numControl;
    }

    public void setNumControl(String numControl) {
        this.numControl = numControl;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEstablecimientoID() {
        return establecimientoID;
    }

    public void setEstablecimientoID(int establecimientoID) {
        this.establecimientoID = establecimientoID;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }
    
    public int getRolID() {
        return rolID;
    }

    public void setRolID(int rolID) {
        this.rolID = rolID;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getNumeroIP() {
        return NumeroIP;
    }

    public void setNumeroIP(String NumeroIP) {
        this.NumeroIP = NumeroIP;
    }

    public String getPrograma() {
        return Programa;
    }

    public void setPrograma(String Programa) {
        this.Programa = Programa;
    }

    public int getAudUsuarioID() {
        return audUsuarioID;
    }

    public void setAudUsuarioID(int audUsuarioID) {
        this.audUsuarioID = audUsuarioID;
    }
    
    public void resetea(){
        usuarioID = null;
        nombre  = null;
        primerApellido= null;
        segundoApellido= null;
        sexo= null;
        fechaNacimiento= null;
        primerAcceso= null;
        numControl= null;
        nombreCompleto= null;
        establecimientoID= 0;
        correo= null;
        clave= null;
        contrasenia= null;
        confirmacion= null;
        rolID= 0;
        estatus= null;

    
    /*SECCION DE AUDITORIA*/
        audUsuarioID= 0;
        claveUsuario= null;
        NumeroIP= null;
        Programa= null;
    }
}
