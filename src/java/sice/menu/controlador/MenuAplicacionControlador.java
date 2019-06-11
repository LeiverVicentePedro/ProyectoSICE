package sice.menu.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;


import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import sice.menu.modelo.ElementoMenu;
import sice.menu.servicio.ElementoMenuServicio;
import sice.usuario.modelo.Usuarios;

/**
 *
 * @author vplei
 */
@ManagedBean
@SessionScoped
public class MenuAplicacionControlador implements Serializable{
    
    private MenuModel modeloMenuPrincipal = new DefaultMenuModel();;
    private List<ElementoMenu> listaMenu = new ArrayList<ElementoMenu>();
    private String redirige = "";
    ElementoMenuServicio elementoMenuServicio = new ElementoMenuServicio();
     
    public void crearMenu(){
        Usuarios usuario = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try{
            listaMenu = elementoMenuServicio.menu(usuario,1);
    
                    for(ElementoMenu menu : listaMenu){
                        if(menu.getTipoMenu().equalsIgnoreCase("s") && menu.getSubmenuID() == 0){
                            DefaultSubMenu menuPrincipal = new DefaultSubMenu(menu.getDescripcion());
                            menuPrincipal.setIcon(menu.getIcono());
                            
                            for(ElementoMenu subMenu : listaMenu){
                                if(subMenu.getSubmenuID() == menu.getElementoMenuID() && subMenu.getTipoMenu().equalsIgnoreCase("s")){
                                    DefaultSubMenu submenu = new DefaultSubMenu(subMenu.getDescripcion());
                                    submenu.setIcon(subMenu.getIcono());
                                   for(ElementoMenu itemMenu : listaMenu){
                                       if(itemMenu.getSubmenuID() == subMenu.getElementoMenuID() && itemMenu.getTipoMenu().equalsIgnoreCase("i")){
                                           DefaultMenuItem item = new DefaultMenuItem(itemMenu.getDescripcion());
                                           item.setIcon(itemMenu.getIcono());
                                           item.setOutcome(itemMenu.getVista());
                                         
                                           submenu.addElement(item);
                                           
                                       }
                                   }
                                    menuPrincipal.addElement(submenu);
                                   
                                }
                            }
                            
                            modeloMenuPrincipal.addElement(menuPrincipal);  
                        } 
                    }
                    
                   

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    //invalida la seccion del usuario
    public void cerrarSesion() throws IOException{
         FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.getExternalContext().invalidateSession();
        contexto.getExternalContext().redirect(contexto.getExternalContext().getRequestContextPath());
        
        
    }
    
    
     public MenuModel getModeloMenuPrincipal() {
        return modeloMenuPrincipal;
    }

    public void setModeloMenuPrincipal(MenuModel modeloMenuPrincipal) {
        this.modeloMenuPrincipal = modeloMenuPrincipal;
    }

    public String getRedirige() {
        return redirige;
    }

    public void setRedirige(String redirige) {
        this.redirige = redirige;
    }
    
}
