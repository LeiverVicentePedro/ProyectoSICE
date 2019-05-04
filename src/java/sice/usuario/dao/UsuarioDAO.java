/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.usuario.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sice.conexion.Conexion;
import sice.usuario.modelo.Usuarios;

/**
 *
 * @author vplei
 */
public class UsuarioDAO extends Conexion{
    
    public UsuarioDAO(){
        super();
    }
    
    public Usuarios ConsultaAcceso(Usuarios usuario, int consulta) throws Exception{
        Usuarios usuarioCon = new Usuarios();
        PreparedStatement preparedStatement;
        ResultSet resultSet;        
        try{
           Conectar();
           String usuariosCon = "CALL USUARIOSCON(?,?,?,?,?,?,?,?,?)";

           preparedStatement = getConexion().prepareStatement(usuariosCon);
           
           preparedStatement.setInt(1, 0);
           preparedStatement.setString(2,"");
           preparedStatement.setString(3, "");
           preparedStatement.setString(4, "");
           preparedStatement.setString(5, "");
           preparedStatement.setString(6, usuario.getClave());
           preparedStatement.setString(7, usuario.getContrasenia());
           preparedStatement.setString(8, "");
           preparedStatement.setInt(9, consulta);

           resultSet = preparedStatement.executeQuery();

          while(resultSet.next()){
           usuarioCon.setUsuarioID(resultSet.getInt("UsuarioID"));
           usuarioCon.setNombre(resultSet.getString("Nombre"));
           usuarioCon.setPrimerApellido(resultSet.getString("PrimerApellido"));
           usuarioCon.setSegundoApellido(resultSet.getString("SegundoApellido"));
           usuarioCon.setSexo(resultSet.getString("Sexo"));
           usuarioCon.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
           usuarioCon.setNumControl(resultSet.getString("NumControl"));
           usuarioCon.setNombreCompleto(resultSet.getString("NombreCompleto"));
           usuarioCon.setEstablecimientoID(resultSet.getInt("EstablecimientoID"));
           usuarioCon.setCorreo(resultSet.getString("Correo"));
           usuarioCon.setClave(resultSet.getString("Clave"));
           usuarioCon.setContrasenia(resultSet.getString("Contrasenia"));
           usuarioCon.setEstatus(resultSet.getString("Estatus"));
           System.out.println("Estatus en dao "+resultSet.getString("Estatus"));
          }
        }catch(Exception ex){
           ex.printStackTrace();
        }finally{
            Cerrar();
           
        }
        
        return usuarioCon;
    }
    
    
}
