/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.conexion;

import java.sql.Connection;
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
    
    public Connection getConexion(){
          return conexion;
    }
    
     public void Conectar() throws Exception{
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
     
     public void Cerrar() throws Exception{
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
