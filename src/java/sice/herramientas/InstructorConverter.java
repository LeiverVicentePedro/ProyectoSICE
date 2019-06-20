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
import sice.usuario.modelo.Instructores;

/**
 *
 * @author vplei
 */
@FacesConverter("instructorConverter")
public class InstructorConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value!=null && value.trim().length()>0){
            Instructores instructor   =   new Instructores();
            instructor.setInstructorID(value);
            return instructor;
        }else{
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value !=null){
        return String.valueOf(value.toString());
        }else{
            return null;
        }
    }
    
}
