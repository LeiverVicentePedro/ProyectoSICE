/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.usuario.servicio;

import java.util.ArrayList;
import java.util.List;
import sice.general.modelo.MensajeSalida;
import sice.usuario.dao.InstructoresDAO;
import sice.usuario.modelo.Instructores;

/**
 *
 * @author vplei
 */
public class InstructoresServicio {
    
    InstructoresDAO instructorDAO = new InstructoresDAO();
    public interface ope_Tran_Instructor{
        int alta = 1;
        int modifica = 2;
    }
    
    public interface ope_Con_Instructor{
        int principal = 1;
    }
    
    public interface ope_Lis_Instructor{
        int principal = 1;
    }
    
    
    public MensajeSalida transaccion(int numTransaccion, Instructores instructor){
        MensajeSalida mensaje = new MensajeSalida();
        try{
             switch(numTransaccion){
                case ope_Tran_Instructor.alta:
                    mensaje = instructorDAO.altaInstructor(instructor);
                break;
                 case ope_Tran_Instructor.modifica:
                    mensaje = instructorDAO.modificaInstructor(instructor);
                break;
             }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return mensaje;
    }
    
    
    public Instructores consulta(int consulta,Instructores instructor){
        Instructores instructorCon = new Instructores();
        try{
            switch(consulta){
                case ope_Con_Instructor.principal:
                    instructorCon = instructorDAO.ConsultaInstructor(instructor, consulta); 
                break;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            
        }
        return instructorCon;
    }
    
     public List<Instructores> lista(int numLista,Instructores usuario){
        List<Instructores> lista = new ArrayList<Instructores>();
        try{
            switch(numLista){
                case ope_Lis_Instructor.principal:
                    lista = instructorDAO.listaPrincipal(usuario, ope_Lis_Instructor.principal);
                break;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }
    
}
