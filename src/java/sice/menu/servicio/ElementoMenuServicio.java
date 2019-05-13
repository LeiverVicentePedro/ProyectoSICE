/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.menu.servicio;

import java.util.List;
import sice.menu.dao.MenuAplicacionDAO;
import sice.menu.modelo.ElementoMenu;
import sice.usuario.modelo.Usuarios;

/**
 *
 * @author vplei
 */
public class ElementoMenuServicio {
 
    MenuAplicacionDAO menuAplicacionDAO = new MenuAplicacionDAO();
    public ElementoMenuServicio(){
        
    }
    
    public List<ElementoMenu> menu(Usuarios usuario ,int numConsulta){
        List<ElementoMenu> listaMenu=null;
        try{
        switch(numConsulta){
            case 1:
               listaMenu = menuAplicacionDAO.menu(usuario.getRolID(), numConsulta);
            break;
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return listaMenu;
    }
}
