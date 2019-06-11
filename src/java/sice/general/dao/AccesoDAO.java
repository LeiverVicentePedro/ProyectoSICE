/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.general.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sice.conexion.Conexion;
import sice.general.modelo.Acceso;
import sice.general.modelo.MensajeSalida;
import sice.usuario.modelo.Usuarios;

/**
 *
 * @author vplei
 */
public class AccesoDAO extends Conexion{
    CallableStatement callableStatement;
    ResultSet resultSet;
    public AccesoDAO() {
        super();
    }
    
    
    
    
    
    public Acceso ConsultaAcceso(String clave, String pass) throws Exception{
        Acceso acceso = new Acceso();
       
                
        try{
           conectar();
           String consulta = "CALL USUARIOSCON(?,?,?,?,?,?,?,?,?)";//Para hacer la llamada aL STORE
           callableStatement = getConexion().prepareCall(consulta);
           
           callableStatement.setInt("Par_UsuarioID", 0);
           callableStatement.setString("Par_Nombre","");
           callableStatement.setString("Par_PrimerApellido", "");
           callableStatement.setString("Par_SegundoApellido", "");
           callableStatement.setString("Par_NumControl", "");
           callableStatement.setString("Par_Clave", clave);
           callableStatement.setString("Par_Contrasenia", pass);
           callableStatement.setString("Par_Estatus", "");
           callableStatement.setInt("Par_Consulta", 1);

            ResultSet resultSet = callableStatement.executeQuery();
            Usuarios usuario = new Usuarios();
          while(resultSet.next()){
           usuario.setUsuarioID(resultSet.getString("UsuarioID"));
           usuario.setNombre(resultSet.getString("Nombre"));
           usuario.setPrimerApellido(resultSet.getString("PrimerApellido"));
           usuario.setSegundoApellido(resultSet.getString("SegundoApellido"));
           usuario.setSexo(resultSet.getString("Sexo"));
           usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
           usuario.setPrimerAcceso(resultSet.getDate("PrimerAcceso"));
           usuario.setNumControl(resultSet.getString("NumControl"));
           usuario.setNombreCompleto(resultSet.getString("NombreCompleto"));
           usuario.setCorreo(resultSet.getString("Correo"));
           usuario.setClave(resultSet.getString("Clave"));
           usuario.setContrasenia(resultSet.getString("Contrasenia"));
           usuario.setRolID(resultSet.getInt("RolID"));
           usuario.setEstatus(resultSet.getString("Estatus"));
          
          }
          acceso.setUsuario(usuario);
        }catch(Exception ex){
           ex.printStackTrace();
        }finally{
            cerrar();
           
        }
        
        return acceso;
    }
    
    
    public MensajeSalida actualizaPassword(Usuarios usuario, int numActualizacion) throws SQLException, Exception{
        MensajeSalida mensaje= new MensajeSalida();
        try{
            conectar();

            String alta = "CALL USUARIOSACT(?,?,?,?,?,"
                                         + "?,?,?,?,?,"
                                         + "?);";
            getConexion().setAutoCommit(false);
            callableStatement = getConexion().prepareCall(alta);
            callableStatement.setInt("Par_UsuarioID",Integer.parseInt(usuario.getUsuarioID()));
            callableStatement.setString("Par_Contrasenia",usuario.getContrasenia());
            callableStatement.setString("Par_Estatus",usuario.getEstatus());
            callableStatement.setInt("Par_NumActualizacion",numActualizacion);
            callableStatement.setString("Par_Salida","S");
            callableStatement.registerOutParameter("Par_NumErr",java.sql.Types.INTEGER);
            callableStatement.registerOutParameter("Par_ErrMen",java.sql.Types.VARCHAR);
            callableStatement.setInt("Aud_UsuarioID",usuario.getAudUsuarioID());
            callableStatement.setString("Aud_ClaveUsuario",usuario.getClaveUsuario());
            callableStatement.setString("Aud_NumeroIP",usuario.getNumeroIP());
            callableStatement.setString("Aud_Programa",usuario.getPrograma());

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
}
