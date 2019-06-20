/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.curso.controlador;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sice.curso.modelo.Cursos;

/**
 *
 * @author vplei
 */
@ManagedBean
@ViewScoped
public class CursosControlador implements Serializable{
    Cursos curso = new Cursos();
    private boolean agregar = true;
    private boolean modificar = true;
    
    public void transaccion(int accion){
        
    }
    
    public List<Cursos> lista(){
        return null;
    }
    
    public void consulta(){
        
    }
    
    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        this.curso = curso;
    }

    public boolean isAgregar() {
        return agregar;
    }

    public void setAgregar(boolean agregar) {
        this.agregar = agregar;
    }

    public boolean isModificar() {
        return modificar;
    }

    public void setModificar(boolean modificar) {
        this.modificar = modificar;
    }

 
    
}
