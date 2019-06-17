/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.herramientas;

/**
 *
 * @author vplei
 */
public class AuditoriasBean {
  /*SECCION DE AUDITORIA*/
   private int audUsuarioID;
   private String claveUsuario;
   private String NumeroIP;
   private String Programa;

    public int getAudUsuarioID() {
        return audUsuarioID;
    }

    public void setAudUsuarioID(int audUsuarioID) {
        this.audUsuarioID = audUsuarioID;
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
    
   
}
