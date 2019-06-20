/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.herramientas;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sice.usuario.modelo.Usuarios;

/**
 *
 * @author vplei
 */
 @FacesConverter("usuarioConverter")
public class UsuarioConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value!=null && value.trim().length()>0){
            System.out.println("CONVERTER OBJETO "+value);
            Usuarios usuario   =   new Usuarios();
            usuario.setUsuarioID(value);
            return usuario;
        }else{
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value !=null){
            System.out.println("CONVERTER CADENA "+value);
        return String.valueOf(value.toString());
        }else{
            return null;
        }
    }
    
}
