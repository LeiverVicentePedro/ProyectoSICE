/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.general.modelo;

import java.io.Serializable;
import sice.usuario.modelo.Usuarios;

/**
 *
 * @author vplei
 */
public class Acceso implements Serializable{
    
    private Usuarios usuario;

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    
}
