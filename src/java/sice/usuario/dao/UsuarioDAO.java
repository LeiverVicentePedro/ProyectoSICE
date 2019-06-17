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
import sice.usuario.modelo.Usuarios;

/**
 *
 * @author vplei
 */
public class UsuarioDAO extends Conexion{
    
    public UsuarioDAO(){
        super();
    }
    
    public Usuarios ConsultaUsuario(Usuarios usuario, int numConsulta) throws Exception{
        Usuarios usuarioCon = new Usuarios();
        
        try{
           conectar();
           String consulta = "CALL USUARIOSCON(?,?,?,?,?,?,?,?,?)";//Para hacer la llamada aL STORE
           
           callableStatement = getConexion().prepareCall(consulta);
           
           callableStatement.setInt("Par_UsuarioID",Integer.parseInt(usuario.getUsuarioID()));
           callableStatement.setString("Par_Nombre","");
           callableStatement.setString("Par_PrimerApellido", "");
           callableStatement.setString("Par_SegundoApellido", "");
           callableStatement.setString("Par_NumControl", "");
           callableStatement.setString("Par_Clave", "");
           callableStatement.setString("Par_Contrasenia", "");
           callableStatement.setString("Par_Estatus", "");
           callableStatement.setInt("Par_Consulta", numConsulta);

           resultSet = callableStatement.executeQuery();

          while(resultSet.next()){
           usuarioCon.setUsuarioID(resultSet.getString("UsuarioID"));
           usuarioCon.setNombre(resultSet.getString("Nombre"));
           usuarioCon.setPrimerApellido(resultSet.getString("PrimerApellido"));
           usuarioCon.setSegundoApellido(resultSet.getString("SegundoApellido"));
           usuarioCon.setSexo(resultSet.getString("Sexo"));
           usuarioCon.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
           usuarioCon.setNumControl(resultSet.getString("NumControl"));
           usuarioCon.setNombreCompleto(resultSet.getString("NombreCompleto"));
           usuarioCon.setCorreo(resultSet.getString("Correo"));
           usuarioCon.setClave(resultSet.getString("Clave"));
           usuarioCon.setContrasenia(resultSet.getString("Contrasenia"));
           usuarioCon.setRolID(resultSet.getInt("RolID"));
           usuarioCon.setEstatus(resultSet.getString("Estatus"));
          
          }
        }catch(Exception ex){
           ex.printStackTrace();
        }finally{
            cerrar();
           
        }
        
        return usuarioCon;
    }
    
    
    public MensajeSalida altaUsuario(Usuarios usuario) throws Exception{
        MensajeSalida mensaje= new MensajeSalida();

        try{
            conectar();

            String alta = "CALL USUARIOSALT(?,?,?,?,?,"
                                         + "?,?,?,?,?,"
                                         + "?,?,?,?,?,"
                                         + "?,?);";
            getConexion().setAutoCommit(false);
            callableStatement = getConexion().prepareCall(alta);
            callableStatement.setString("Par_Nombre",usuario.getNombre());
            callableStatement.setString("Par_PrimerApellido",usuario.getPrimerApellido());
            callableStatement.setString("Par_SegundoApellido",usuario.getSegundoApellido());
            callableStatement.setString("Par_Sexo",usuario.getSexo());
            callableStatement.setDate("Par_FechaNacimiento",new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            callableStatement.setString("Par_NumControl",usuario.getNumControl());
            callableStatement.setString("Par_Correo",usuario.getCorreo());
            callableStatement.setString("Par_Clave",usuario.getClave());
            callableStatement.setString("Par_Contrasenia",usuario.getContrasenia());
            callableStatement.setInt("Par_RolID",usuario.getRolID());
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
    
    public MensajeSalida modificaUsuario(Usuarios usuario) throws Exception{
        MensajeSalida mensaje= new MensajeSalida();

        try{
            conectar();

            String alta = "CALL USUARIOSMOD(?,?,?,?,?,"
                                         + "?,?,?,?,?,"
                                         + "?,?,?,?,?,"
                                         + "?,?,?);";
            getConexion().setAutoCommit(false);
            callableStatement = getConexion().prepareCall(alta);
            callableStatement.setInt("Par_UsuarioID",Integer.parseInt(usuario.getUsuarioID()));
            callableStatement.setString("Par_Nombre",usuario.getNombre());
            callableStatement.setString("Par_PrimerApellido",usuario.getPrimerApellido());
            callableStatement.setString("Par_SegundoApellido",usuario.getSegundoApellido());
            callableStatement.setString("Par_Sexo",usuario.getSexo());
            callableStatement.setDate("Par_FechaNacimiento",new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            callableStatement.setString("Par_NumControl",usuario.getNumControl());
            callableStatement.setString("Par_Correo",usuario.getCorreo());
            callableStatement.setString("Par_Clave",usuario.getClave());
            callableStatement.setString("Par_Contrasenia",usuario.getContrasenia());
            callableStatement.setInt("Par_RolID",usuario.getRolID());
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
    
    
    public List<Usuarios> listaPrincipal(Usuarios usuario,int numLista) throws Exception{
        List<Usuarios> lista = new ArrayList<Usuarios>();
        try{
            conectar();
            String listaSQL = "CALL USUARIOSLIS(?,?,?,?,?);";
            callableStatement = getConexion().prepareCall(listaSQL);
            callableStatement.setString("Par_UsuarioID", usuario.getUsuarioID());
            callableStatement.setString("Par_Nombre","");
            callableStatement.setString("Par_PrimerApe", "");
            callableStatement.setString("Par_SegundoApe","");
            callableStatement.setInt("Par_NumLista", numLista);
            resultSet = callableStatement.executeQuery();
            while(resultSet.next()){
                Usuarios usuarioRes = new Usuarios();
                usuarioRes.setUsuarioID(resultSet.getString("UsuarioID"));
                usuarioRes.setNombreCompleto(resultSet.getString("NombreCompleto"));
                lista.add(usuarioRes);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            cerrar();
        }
        return lista;
    }
}
