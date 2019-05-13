package sice.menu.modelo;

import java.io.Serializable;

/**
 *
 * @author vplei
 */
public class ElementoMenu implements Serializable{
   private int elementoMenuID;
   private String descripcion;
   private String nombreMenu;
   private String tipoMenu;
   private int submenuID;
   private int orden;
   private String estatus;
   private String icono;
   private String vista;

    public int getElementoMenuID() {
        return elementoMenuID;
    }

    public void setElementoMenuID(int elementoMenuID) {
        this.elementoMenuID = elementoMenuID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public String getTipoMenu() {
        return tipoMenu;
    }

    public void setTipoMenu(String tipoMenu) {
        this.tipoMenu = tipoMenu;
    }

    public int getSubmenuID() {
        return submenuID;
    }

    public void setSubmenuID(int submenuID) {
        this.submenuID = submenuID;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }
   
   
   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.elementoMenuID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ElementoMenu other = (ElementoMenu) obj;
        if (this.elementoMenuID != other.elementoMenuID) {
            return false;
        }
        return true;
    }
}
