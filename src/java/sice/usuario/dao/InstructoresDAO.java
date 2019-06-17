/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.usuario.dao;


import java.util.ArrayList;
import java.util.List;
import sice.conexion.Conexion;
import sice.general.modelo.MensajeSalida;
import sice.usuario.modelo.Instructores;
import sice.usuario.modelo.Usuarios;

/**
 *
 * @author vplei
 */
public class InstructoresDAO extends Conexion{

    public InstructoresDAO() {
        super();
    }
    
    public Instructores ConsultaInstructor(Instructores instructor, int numConsulta) throws Exception{
        Instructores instructorCon = new Instructores();
        
        try{
           conectar();
           String consulta = "CALL INSTRUCTORESCON(?,?,?,?,?)";//Para hacer la llamada aL STORE
           
           callableStatement = getConexion().prepareCall(consulta);
           
           callableStatement.setInt("Par_InstructorID",Integer.parseInt(instructor.getInstructorID()));
           callableStatement.setInt("Par_UsuarioID",0);
           callableStatement.setString("Par_Clave", "");
           callableStatement.setString("Par_Estatus", "");
           callableStatement.setInt("Par_NumConsulta", numConsulta);
           
           resultSet = callableStatement.executeQuery();

          while(resultSet.next()){
            instructorCon.setInstructorID(resultSet.getString("InstructorID"));
            Usuarios usuario =new Usuarios();
            usuario.setUsuarioID(resultSet.getString("UsuarioID"));
            instructorCon.setUsuarioID(usuario);
            instructorCon.setClave(resultSet.getString("Clave"));
            instructorCon.setEstatus(resultSet.getString("Estatus"));
          }
        }catch(Exception ex){
           ex.printStackTrace();
        }finally{
            cerrar();
           
        }
        
        return instructorCon;
    }
    
    
    public MensajeSalida altaInstructor(Instructores instructor) throws Exception{
        MensajeSalida mensaje= new MensajeSalida();

        try{
            conectar();

            String alta = "CALL INSTRUCTORESALT(?,?,?,?,?,"
                                         + "?,?,?,?,?);";
            getConexion().setAutoCommit(false);
            callableStatement = getConexion().prepareCall(alta);
            callableStatement.setInt("Par_UsuarioID",Integer.parseInt(instructor.getUsuarioID().getUsuarioID()));
            callableStatement.setString("Par_Clave",instructor.getClave());
            callableStatement.setString("Par_Estatus",instructor.getEstatus());
            
            callableStatement.setString("Par_Salida","S");
            callableStatement.registerOutParameter("Par_NumErr",java.sql.Types.INTEGER);
            callableStatement.registerOutParameter("Par_ErrMen",java.sql.Types.VARCHAR);
            callableStatement.setInt("Aud_UsuarioID",instructor.getAudUsuarioID());
            callableStatement.setString("Aud_ClaveUsuario",instructor.getClaveUsuario());
            callableStatement.setString("Aud_NumeroIP",instructor.getNumeroIP());
            callableStatement.setString("Aud_Programa",instructor.getPrograma());

             resultSet = callableStatement.executeQuery();
             getConexion().commit();
             while(resultSet.next()){
             mensaje.setNumErr(resultSet.getInt("NumErr"));
             mensaje.setErrMen(resultSet.getString("ErrMen"));
             mensaje.setControl(resultSet.getString("Control"));
             mensaje.setConsecutivo(resultSet.getInt("Consecutivo"));
             }
 
        }catch(Exception ex){
            ex.printStackTrace();
            getConexion().rollback();
            getConexion().setAutoCommit(true);
        }finally{
             
            cerrar();
            
        }
        return mensaje;
    }
    
    public MensajeSalida modificaInstructor(Instructores instructor) throws Exception{
        MensajeSalida mensaje= new MensajeSalida();

        try{
            conectar();

            String alta = "CALL INSTRUCTORESMOD(?,?,?,?,?,"
                                         + "?,?,?,?,?);";
            getConexion().setAutoCommit(false);
            callableStatement = getConexion().prepareCall(alta);
            callableStatement.setInt("Par_InstructorID", Integer.parseInt(instructor.getInstructorID()));
            callableStatement.setInt("Par_UsuarioID",Integer.parseInt(instructor.getUsuarioID().getUsuarioID()));
            callableStatement.setString("Par_Clave",instructor.getClave());

            callableStatement.setString("Par_Salida","S");
            callableStatement.registerOutParameter("Par_NumErr",java.sql.Types.INTEGER);
            callableStatement.registerOutParameter("Par_ErrMen",java.sql.Types.VARCHAR);
            callableStatement.setInt("Aud_UsuarioID",instructor.getAudUsuarioID());
            callableStatement.setString("Aud_ClaveUsuario",instructor.getClaveUsuario());
            callableStatement.setString("Aud_NumeroIP",instructor.getNumeroIP());
            callableStatement.setString("Aud_Programa",instructor.getPrograma());

             resultSet = callableStatement.executeQuery();
             getConexion().commit();
             while(resultSet.next()){
             mensaje.setNumErr(resultSet.getInt("NumErr"));
             mensaje.setErrMen(resultSet.getString("ErrMen"));
             mensaje.setControl(resultSet.getString("Control"));
             mensaje.setConsecutivo(resultSet.getInt("Consecutivo"));
             }
 
        }catch(Exception ex){
            ex.printStackTrace();
            getConexion().rollback();
            getConexion().setAutoCommit(true);
        }finally{
             
            cerrar();
            
        }
        return mensaje;
    }
    
    
    
    public List<Instructores> listaPrincipal(Instructores instructor,int numLista) throws Exception{
        List<Instructores> lista = new ArrayList<Instructores>();
        try{
            conectar();
            String listaSQL = "CALL INSTRUCTORESLIS(?,?,?);";
            callableStatement = getConexion().prepareCall(listaSQL);
            callableStatement.setString("Par_InstructorID",instructor.getInstructorID() );
            callableStatement.setString("Par_Clave","");
            callableStatement.setInt("Par_NumLista", numLista);
            resultSet = callableStatement.executeQuery();
            while(resultSet.next()){
                Instructores instructorLis = new Instructores();
                instructorLis.setInstructorID(resultSet.getString("InstructorID"));
                instructorLis.setNombreCompleto(resultSet.getString("NombreCompleto"));
                lista.add(instructorLis);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            cerrar();
        }
        return lista;
    }
}
