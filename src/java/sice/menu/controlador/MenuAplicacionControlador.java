package sice.menu.controlador;

import java.io.Serializable;
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
    private List<ElementoMenu> listaMenu;
    
    ElementoMenuServicio elementoMenuServicio = new ElementoMenuServicio();
     
    public void crearMenu(){
        Usuarios usuario = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try{
            listaMenu = elementoMenuServicio.menu(usuario,1);
    
                    for(ElementoMenu menu : listaMenu){
                        if(menu.getTipoMenu().equalsIgnoreCase("s") && menu.getSubmenuID() == 0){
                            DefaultSubMenu menuPrincipal = new DefaultSubMenu(menu.getDescripcion());
                            menuPrincipal.setIcon(menu.getIcono());
                            System.out.println("submenu "+menu.getDescripcion());
                            
                            for(ElementoMenu subMenu : listaMenu){
                                if(subMenu.getSubmenuID() == menu.getElementoMenuID() && subMenu.getTipoMenu().equalsIgnoreCase("s")){
                                    DefaultSubMenu submenu = new DefaultSubMenu(subMenu.getDescripcion());
                                    submenu.setIcon(subMenu.getIcono());
                                   for(ElementoMenu itemMenu : listaMenu){
                                       if(itemMenu.getSubmenuID() == subMenu.getElementoMenuID() && itemMenu.getTipoMenu().equalsIgnoreCase("i")){
                                           DefaultMenuItem item = new DefaultMenuItem(itemMenu.getDescripcion());
                                           item.setIcon(itemMenu.getIcono());
                                           submenu.addElement(item);
                                           
                                       }
                                   }
                                    System.out.println("item "+subMenu.getDescripcion());
                                    menuPrincipal.addElement(submenu);
                                }
                            }
                            modeloMenuPrincipal.addElement(menuPrincipal);
                        }
                    }
                    
                   

        }catch(Exception ex){
            System.out.println("Error en MenuBEAN -> crearMenu: "+ex);
            ex.printStackTrace();
        }
    }
    
    
    public void exite() {
        try {
            FacesContext contexto = FacesContext.getCurrentInstance();
            Usuarios usuarioVive = (Usuarios) contexto.getExternalContext().getSessionMap().get("usuario");
            System.out.println("Existe "+usuarioVive.getClave()+" "+usuarioVive.getRolID());
            if (usuarioVive.getClave()== null) {
                contexto.getExternalContext().redirect("index.xhtml");  
            }else{
               usuarioVive = usuarioVive; 
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
    
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    
     public MenuModel getModeloMenuPrincipal() {
        return modeloMenuPrincipal;
    }

    public void setModeloMenuPrincipal(MenuModel modeloMenuPrincipal) {
        this.modeloMenuPrincipal = modeloMenuPrincipal;
    }
}
