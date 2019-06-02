/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.menu.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import sice.conexion.Conexion;
import sice.menu.modelo.ElementoMenu;

/**
 *
 * @author vplei
 */
public class MenuAplicacionDAO extends Conexion{

    public MenuAplicacionDAO() {
        super();
    }
    
    public List<ElementoMenu> menu(int rolID, int numConsulta) throws Exception{
        CallableStatement callableStatement;
        ResultSet resultSet;
        List<ElementoMenu> listaMenu = new ArrayList<ElementoMenu>();
        try{
            conectar();
            String consulta = "CALL MENUCON(?,?);";
            callableStatement = getConexion().prepareCall(consulta);
            callableStatement.setInt("Par_RolID",rolID);
            callableStatement.setInt("Par_NumConsulta",numConsulta);
            
            resultSet = callableStatement.executeQuery();
             while(resultSet.next()){
                ElementoMenu elementoMenu = new ElementoMenu();
                elementoMenu.setElementoMenuID(resultSet.getInt("ElementoMenuID"));
                 elementoMenu.setDescripcion(resultSet.getString("NombreMenu"));
                 elementoMenu.setTipoMenu(resultSet.getString("TipoMenu"));
                 elementoMenu.setSubmenuID(resultSet.getInt("SubmenuID"));
                 elementoMenu.setOrden(resultSet.getInt("Orden"));
                 elementoMenu.setIcono(resultSet.getString("Icono"));
                 elementoMenu.setVista(resultSet.getString("Vista"));
                 listaMenu.add(elementoMenu);
             }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            cerrar();
        }
       return listaMenu;
    }
}
