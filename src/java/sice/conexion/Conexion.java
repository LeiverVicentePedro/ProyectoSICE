package sice.conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author vplei
 */
public class Conexion {
    @Resource(mappedName="jdbc/sice_pool")
    private DataSource dataSource = null;
    
    private Connection conexion;
    private InitialContext context;
    
    public CallableStatement callableStatement;
    public ResultSet resultSet;
    
    public Connection getConexion(){
          return conexion;
    }
    
     public void conectar() throws Exception{
        try{
           System.out.println("======= SE CREA CONEXION =========");
           context  = new InitialContext();
           dataSource = (DataSource) context.lookup("jdbc/sice_pool");
           conexion = dataSource.getConnection();
           System.out.println("======== CONEXION CREADA ========");
        }
        catch(Exception ex){
            System.out.println("Error de Conexion" +ex+" ==============================");
            throw ex;
        }
    }
     
     public void cerrar() throws Exception{
        try{
        if(conexion != null){
            if(conexion.isClosed()==false){
                conexion.close();
                System.out.println("===== SE  CIERRA CONEXION ===========");
            }
        }
        }catch(Exception ex){
            throw ex;
        }
    }
}
